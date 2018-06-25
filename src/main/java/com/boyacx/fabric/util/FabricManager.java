package com.boyacx.fabric.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import com.boyacx.fabric.ChaincodeManager;
import com.boyacx.fabric.FabricConfig;
import com.boyacx.fabric.bean.Chaincode;
import com.boyacx.fabric.bean.Orderers;
import com.boyacx.fabric.bean.Peers;

/**
 * FabricManager管理器,包含两个简单部分，第一部分是项目配置，第二部分是调用方法。
 * 
 * @author linrol
 *
 * @date 2018年06月25日 - 下午1:49:03
 * @email 1071893649@qq.com
 */
public class FabricManager {

    private ChaincodeManager manager;

    private static FabricManager instance = null;
    
    private final static  String channelName = "foo" ;

    public static FabricManager obtain()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException {
        if (null == instance) {
            synchronized (FabricManager.class) {
                if (null == instance) {
                    instance = new FabricManager();
                }
            }
        }
        return instance;
    }

    private FabricManager() throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException {
        try {
			manager = new ChaincodeManager(getConfig());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 获取节点服务器管理器
     * 
     * @return 节点服务器管理器
     */
    public ChaincodeManager getManager() {
        return manager;
    }

    /**
     * 根据节点作用类型获取节点服务器配置
     * 
     * @param type
     *            服务器作用类型（1、执行；2、查询）
     * @return 节点服务器配置
     */
    private FabricConfig getConfig() {
        FabricConfig config = new FabricConfig();
        config.setOrderers(getOrderers());
        config.setPeers(getPeers());
        config.setChaincode(getChaincode(channelName, "example_cc_go", "github.com/example_cc", "1"));
        config.setChannelArtifactsPath(getChannleArtifactsPath());
        config.setCryptoConfigPath(getCryptoConfigPath());
        return config;
    }

    private Orderers getOrderers() {
        Orderers orderer = new Orderers();
        orderer.setOrdererDomainName("example.com");
        orderer.addOrderer("orderer.example.com", "grpc://211.159.178.116:7050");
        //orderer.addOrderer("orderer0.example.com", "grpc://211.159.178.116:7050");
        //orderer.addOrderer("orderer2.example.com", "grpc://211.159.178.116:7050");
        return orderer;
    }

    /**
     * 获取节点服务器集
     * 
     * @return 节点服务器集
     */
    private Peers getPeers() {
        Peers peers = new Peers();
        peers.setOrgName("Org1");
        peers.setOrgMSPID("Org1MSP");
        peers.setOrgDomainName("org1.example.com");
        peers.addPeer("peer0.org1.example.com", "peer0.org1.example.com", "grpc://211.159.178.116:7051", "grpc://211.159.178.116:7053", "http://211.159.178.116:7054");
        return peers;
    }

    /**
     * 获取智能合约
     * 
     * @param channelName
     *            频道名称
     * @param chaincodeName
     *            智能合约名称
     * @param chaincodePath
     *            智能合约路径
     * @param chaincodeVersion
     *            智能合约版本
     * @return 智能合约
     */
    private Chaincode getChaincode(String channelName, String chaincodeName, String chaincodePath, String chaincodeVersion) {
        Chaincode chaincode = new Chaincode();
        chaincode.setChannelName(channelName);
        chaincode.setChaincodeName(chaincodeName);
        chaincode.setChaincodePath(chaincodePath);
        chaincode.setChaincodeVersion(chaincodeVersion);
        chaincode.setInvokeWatiTime(100000);
        chaincode.setDeployWatiTime(120000);
        return chaincode;
    }

    /**
     * 获取channel-artifacts配置路径
     * 
     * @return /WEB-INF/classes/fabric/channel-artifacts/
     */
    private String getChannleArtifactsPath() {
        String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
        System.err.println("directorys = " + directorys);
        File directory = new File(directorys);
        System.err.println("directory = " + directory.getPath());

        return directory.getPath() + "/channel-artifacts/";
    }

    /**
     * 获取crypto-config配置路径
     * 
     * @return /WEB-INF/classes/fabric/crypto-config/
     */
    private String getCryptoConfigPath() {
        String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
        System.err.println("directorys = " + directorys);
        File directory = new File(directorys);
        System.err.println("directory = " + directory.getPath());
        return directory.getPath() + "/crypto-config/";
    }

}
