package com.test.tw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ProductTest {

	ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	static Map<String, Integer> data ;
	static {
		data = new HashMap<>();
		data.put("ar01", 35);
		data.put("ar02", 45);
	}
	
	public CompletableFuture<Integer> getPrice(String id){
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

		    return data.get(id);
		});
		
		return future;
	}
	
	public void onPrice(Integer price){
		System.out.println(price);
	}
	
	public void totalPriceParallel(String id1, String id2) throws InterruptedException, ExecutionException{
		final CompletableFuture<Integer> f1 = getPrice("ar01");
		final CompletableFuture<Integer> f2 = getPrice("ar02");
		CompletableFuture.allOf(f1,f2).thenRun(new Runnable() {
			
			@Override
			public void run() {
				try {
					onPrice(f1.get()+f2.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ProductTest test = new ProductTest();
		
		test.totalPriceSeq("ar01", "ar02");
		Thread.sleep(5000);
	}

	public void totalPriceSeq(String id1, String id2){
		CompletableFuture<Integer> future1 = getPrice(id1);

		CompletableFuture<Integer> future2 = getPrice(id2);


		CompletableFuture<Integer> combinedFuture = future1
		        .thenCombine(future2, (value1, value2) -> {
		    Integer total = value1+value2;
		    return total;
		});

		try {
			System.out.println(combinedFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
