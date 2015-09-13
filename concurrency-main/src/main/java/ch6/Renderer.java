package ch6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.CIOUtil;
import util.ExcptionUtil;

public class Renderer {
	private final ExecutorService executor;

	Renderer(ExecutorService executor) {
		this.executor = executor;
	}

	void renderPage(CharSequence source) {
		final List<ImageInfo> info = scanForImageInfo(source);
		CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(
				executor);
		for (final ImageInfo imageInfo : info)
			completionService.submit(new Callable<ImageData>() {
				@Override
				public ImageData call() {
					return imageInfo.downloadImage();
				}
			});
		    renderText();
		try {
			for (int t = 0, n = info.size(); t < n; t++) {
				Future<ImageData> f = completionService.take();
				ImageData imageData = f.get();
				renderImage(imageData);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			throw ExcptionUtil.launderThrowable(e.getCause());
		}
	}
	
	
	private void renderText() { 
		
	}
	
	private void renderImage(ImageData imageData) { 
				
	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		
		ArrayList<ImageInfo> images = new ArrayList<ImageInfo>();
		String url =  (String)source;
		Document doc = CIOUtil.parseHtmlUrl(url);
		Elements els = doc.getElementsByTag("img");
		ListIterator<Element> it = els.listIterator();
		while(it.hasNext()) {
			Element e = it.next();
			String src = e.attr("src");
			images.add(new ImageInfo(url + "/" + src));
		}
		
		return images;
	}

	static class ImageInfo {
		
		CharSequence url; 
		
		public ImageInfo(CharSequence url) { 
			this.url = url;
		}
		
		public ImageData downloadImage() {
			try {
				ImageData imageData = new ImageData(CIOUtil.getBytesFromUrl((String)url));
				System.out.println("Bytes read:" + imageData.getBytes().length);
				return imageData;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

	static class ImageData {
		byte[] bytes;
		
		public ImageData(byte[] bytes) { 
			this.bytes = bytes;
		}
		
		public byte[] getBytes() {
			return bytes;
		}

	}
	
    public static void main(String args[]) { 
    	ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
    	Renderer renderer = new Renderer(newFixedThreadPool);
    	renderer.renderPage("http://www.impactcoatings.com");
    }

}