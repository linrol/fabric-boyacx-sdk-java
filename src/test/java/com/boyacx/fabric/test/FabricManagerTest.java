package com.boyacx.fabric.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.junit.Test;

import com.boyacx.fabric.ChaincodeManager;
import com.boyacx.fabric.util.FabricManager;

/** 
* @author 罗林 E-mail:1071893649@qq.com 
* @version 创建时间：2018年6月24日 下午12:20:12 
* 类说明 
*/
public class FabricManagerTest{
	
	/**
	 * 创建账户测试用例
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
        ChaincodeManager manager = FabricManager.obtain().getManager();
        String fcn = "create" ;
        String[] arguments = new String[]{"c","10"};
        manager.invoke(fcn, arguments);
        arguments = new String[]{"d","50"};
        manager.invoke(fcn, arguments);
    }
	
	/**
	 * 账户之间转账测试用例
	 * @throws Exception
	 */
	@Test
	public void testMove() throws Exception {
		ChaincodeManager manager = FabricManager.obtain().getManager();
		String fcn = "move" ;
        String[] arguments = new String[]{"i1","i0","10"};
        manager.invoke(fcn, arguments);
	}
    
	/**
	 * 查询账户的测试用例
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
        ChaincodeManager manager = FabricManager.obtain().getManager();
        String fcn = "query" ;
        String[] arguments = new String[]{"c"};
        manager.query(fcn, arguments);
        arguments = new String[]{"d"};
        manager.query(fcn, arguments);
    }
	
	@Test
	public void testMultiCreate() throws Exception {
		ChaincodeManager manager = FabricManager.obtain().getManager();
		String fcn = "create" ;
		for(int i=0;i<100;i++){
			String[] arguments = new String[]{"i" + i,"100"};
			manager.invoke(fcn, arguments);
		}
	}
	
	@Test
	public void testMultiQuery() throws Exception {
		ChaincodeManager manager = FabricManager.obtain().getManager();
		String fcn = "query" ;
		for(int i=0;i<100;i++){
			String[] arguments = new String[]{"i" + i};
			manager.query(fcn, arguments);
		}
	}
	
	@Test
	public void testThreadMove() throws Exception {
		ChaincodeManager manager = FabricManager.obtain().getManager();
		for(int i=1;i<10;i++) {
			/*String[] arguments = new String[]{"i" + i,"i0","10"};
			Map<String, String> invokeMape = manager.invoke("move", arguments);
			System.out.println("i" + i + "..." + invokeMape.get("code") + "..." + invokeMape.get("data"));
			Thread.sleep(3000);*/
			Thread thread = new MoveThreadTest(manager,"i" + i);
			thread.start();
		}
	}
	
	public class MoveThreadTest extends Thread {  
	    private String name;
	    
	    private ChaincodeManager manager;
	    
	    public MoveThreadTest(ChaincodeManager manager, String name){  
	        this.manager = manager;  
	        this.name = name;
	    }
	  
	    public void run() {
	    	System.out.println(name + " move i0 10 money");
	        String[] arguments = new String[]{name,"i0","10"};
	        try {
				manager.invoke("move", arguments);
			} catch (InvalidArgumentException | ProposalException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | CryptoException | TransactionException | InterruptedException | ExecutionException | TimeoutException | IOException e) {
				e.printStackTrace();
			}
	    }  
	}
}
