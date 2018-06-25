package com.boyacx.fabric.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
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
	
	@Test
	public void testCreate() throws IOException, NoSuchAlgorithmException, InvocationTargetException, NoSuchMethodException, InstantiationException, InvalidKeySpecException, CryptoException, InvalidArgumentException, IllegalAccessException, NoSuchProviderException, TransactionException, ClassNotFoundException, InterruptedException, ExecutionException, TimeoutException, ProposalException {
        ChaincodeManager manager = FabricManager.obtain().getManager();
        String fcn = "create" ;
        String[] arguments = new String[]{"c","10"};
        manager.invoke(fcn, arguments);
        arguments = new String[]{"d","50"};
        manager.invoke(fcn, arguments);
    }
	
	@Test
	public void testMove() throws Exception {
		ChaincodeManager manager = FabricManager.obtain().getManager();
		String fcn = "move" ;
        String[] arguments = new String[]{"SysUser","18883754124","300"};
        manager.invoke(fcn, arguments);
	}
    
	@Test
	public void testQuery() throws IOException, NoSuchAlgorithmException, InvocationTargetException, NoSuchMethodException, InstantiationException, InvalidKeySpecException, CryptoException, InvalidArgumentException, IllegalAccessException, NoSuchProviderException, TransactionException, ClassNotFoundException, InterruptedException, ExecutionException, TimeoutException, ProposalException {
        ChaincodeManager manager = FabricManager.obtain().getManager();
        String fcn = "query" ;
        String[] arguments = new String[]{"c"};
        manager.query(fcn, arguments);
        arguments = new String[]{"d"};
        manager.query(fcn, arguments);
    }
}
