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

import com.boyacx.fabric.ChaincodeManager;
import com.boyacx.fabric.util.FabricManager;

import junit.framework.TestCase;

/** 
* @author 罗林 E-mail:1071893649@qq.com 
* @version 创建时间：2018年6月24日 下午12:20:12 
* 类说明 
*/
public class FabricManagerTest extends TestCase{
	
	/*public void testInit() throws Exception {
		
		End2endIT end2endIT = new End2endIT();
		
		File sampleStoreFile = new File(System.getProperty("java.io.tmpdir") + "/HFCSampletest.properties");
		
		if (sampleStoreFile.exists()) { //For testing start fresh
            sampleStoreFile.delete();
        }
		SampleStore sampleStore = new SampleStore(sampleStoreFile);

		end2endIT.enrollUsersSetup(sampleStore); //This enrolls users with fabric ca and setups sample store to get users later.
		HFClient client = HFClient.createNewInstance();//初始化一个链接客户端，类似cli
		client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());//设置加密算法
		System.out.println("init client success");
		
		//Construct and run the channels
		// 获取peerOrg1组织1
		TestConfig testConfig = TestConfig.getConfig();
		SampleOrg sampleOrg = testConfig.getIntegrationTestsSampleOrg("peerOrg1");
		// 构建一个channel通道，Org1加入到该通道中
		Channel fooChannel = end2endIT.constructChannel("foo", client, sampleOrg);
		// 保存通道名称到数据库中(这里是存储到上面方法文件)
		sampleStore.saveChannel(fooChannel);
		// 安装链码、实例化链码、执行一个查询测试
		end2endIT.runChannel(client, fooChannel, true, sampleOrg, 0);
		
	}*/
	
	/*public void testCreate() throws IOException, NoSuchAlgorithmException, InvocationTargetException, NoSuchMethodException, InstantiationException, InvalidKeySpecException, CryptoException, InvalidArgumentException, IllegalAccessException, NoSuchProviderException, TransactionException, ClassNotFoundException, InterruptedException, ExecutionException, TimeoutException, ProposalException {
        ChaincodeManager manager = FabricManager.obtain().getManager();
        String fcn = "create" ;
        String[] arguments = new String[]{"c","10"};
        manager.invoke(fcn, arguments);
        arguments = new String[]{"d","50"};
        manager.invoke(fcn, arguments);
    }*/
	
	/*public void testMove() throws Exception {
		ChaincodeManager manager = FabricManager.obtain().getManager();
		String fcn = "move" ;
        String[] arguments = new String[]{"SysUser","18883754124","300"};
        manager.invoke(fcn, arguments);
	}*/
    
	public void testQuery() throws IOException, NoSuchAlgorithmException, InvocationTargetException, NoSuchMethodException, InstantiationException, InvalidKeySpecException, CryptoException, InvalidArgumentException, IllegalAccessException, NoSuchProviderException, TransactionException, ClassNotFoundException, InterruptedException, ExecutionException, TimeoutException, ProposalException {
        ChaincodeManager manager = FabricManager.obtain().getManager();
        String fcn = "query" ;
        String[] arguments = new String[]{"c"};
        manager.query(fcn, arguments);
        arguments = new String[]{"d"};
        manager.query(fcn, arguments);
    }
	
}
