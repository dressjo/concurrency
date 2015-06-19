package ch5_1;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ch5_1.binarytree.Binary23Tree;
import ch5_1.binarytree.StringNode;

public class IndexTest {
	
	public static void main(String args[]) {
		
		File[] root = new File[] {new File("c:/eclipse")};
		BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>();
		Binary23Tree<String> index = new  Binary23Tree<String>(new StringNode());
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File pathname) {
				return true;
			}			
		};
		
		List<Thread> crawlerThreads = new ArrayList<Thread>(); 
		int j=1;
		for(File file : root) {
			Thread t = new Thread(new FileCrawler(fileQueue, fileFilter, file, "" + j++));
			crawlerThreads.add(t);
			t.start();
		}
			
		List<Thread> indexerThreads = new ArrayList<Thread>(); 
		j = 1;
		for(int i=0; i < 2; i++) {
			Thread t= new Thread(new Indexer(fileQueue, index, "" + j));
			indexerThreads.add(t);
			t.start();
		}
		
		try {
			Thread.sleep(20000);
			stopThreads(crawlerThreads);
			stopThreads(indexerThreads);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		index.print();
		
	}
	
	private static void stopThreads(List<Thread> threadList) {
		for(Thread thread : threadList) {
			thread.interrupt();
		}
	}

}
