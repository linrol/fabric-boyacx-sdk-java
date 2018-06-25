package com.boyacx.fabric.test;

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
        String[] arguments = new String[]{"SysUser","18883754124","300"};
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
}
