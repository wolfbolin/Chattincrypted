package msgpack;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.msgpack.*;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.core.buffer.MessageBufferInput;
import org.msgpack.value.Value;
import org.msgpack.value.ValueFactory;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

public class msgpackDemo 
{
    public static void main( String[] args ) throws Exception {
    	
    	//打包字符串
    	MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
    	packer.packString("action");
    	packer.packString("register");
    	packer.packString("username");
    	packer.packString("test");
    	packer.packString("password");
    	packer.packString("pwd");
    	
    	packer.close();
    	 
    	byte[] context = packer.toByteArray();
    	 
    	 //数据解包
    	MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(context);
    	String [] unpackString = new String [6];
    	for(int i = 0 ; i<6 ; i++) {
    		 unpackString[i] = unpacker.unpackString();
    		 System.out.println(unpackString[i]);
    	 }
    	
    	//对文件进行打包
    	MessageBufferPacker packer2 = MessagePack.newDefaultBufferPacker();
    	
    	byte [] fileDate = getBytes("D:/123.txt");
    	int length = fileDate.length;
    	System.out.println("文件字节长度："+ length);
    	packer2.packBinaryHeader(length); 
        packer2.writePayload(fileDate);
        packer2.close();
        
        byte [] FileContext = packer2.toByteArray();
        
       
        //对文件进行解包
        MessageUnpacker unpacker2 = MessagePack.newDefaultUnpacker(FileContext);
        byte [] unpackerFile =  unpacker2.readPayload(unpacker2.unpackBinaryHeader());
        getFile(unpackerFile, "D:/","456.txt");
              
    }
    	 
    //根据文件路径生成byte数组的方法	 
    private static byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);
            int length = (int) file.length();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(length);  
            byte[] b = new byte[length];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    //通过byte数组生成文件的方法
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }  
    
    
    
    
    public static byte[] getBytes(int data)
    {
    	byte[] bytes = new byte[4];
    	bytes[0] = (byte) (data & 0xff);
    	bytes[1] = (byte) ((data & 0xff00) >> 8);
    	bytes[2] = (byte) ((data & 0xff0000) >> 16);
    	bytes[3] = (byte) ((data & 0xff000000) >> 24);
    	return bytes;
    }
    public static int getInt(byte[] bytes)
    {
    	return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }
    
    
     	
}


