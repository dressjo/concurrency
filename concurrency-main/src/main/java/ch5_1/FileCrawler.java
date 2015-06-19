package ch5_1;

import static java.lang.System.out;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable {
	
	private final BlockingQueue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;
	private final String name;
	
	public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter,
			File root, String name) {
		super();
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void run() {
		try {
			crawl(root);
		} catch(InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if(entries != null) {
			for(File entry : entries) {
				if(entry.isDirectory()) {
					crawl(entry);
				} else if(true) {
					out.println(String.format("Thread [%1s] queue %2s", this.getName(), entry.getName()));
					fileQueue.put(entry);
				}
			}
		}
	}

}
