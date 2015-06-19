package ch5_1;

import static java.lang.System.out;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import ch5_1.binarytree.Binary23Tree;

public class Indexer implements Runnable {

	private final Binary23Tree<String> index;
	private final BlockingQueue<File> queue;
	private final String name;
	
	public Indexer(BlockingQueue<File> queue, Binary23Tree<String> index, String name) {
		super();
		this.index = index;
		this.queue = queue;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void run() {
	    try {
	    	while(true) {
	    		File file = queue.take();
	    		out.println(String.format("Thread[%1s] indexing: %2s", this.name, file.getName()));
	    		indexFile(file);
	    	}	    	
	    } catch(InterruptedException ie) {
	    	Thread.currentThread().interrupt();
	    }
	}
	
	private void indexFile(File f) {
		index.insert(f.getName());
	}

}
