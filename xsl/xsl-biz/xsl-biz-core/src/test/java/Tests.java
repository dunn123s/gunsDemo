import com.chuang.urras.support.exception.SystemWarnException;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Tests {

    public static final String FILE_PATH = "C:\\Users\\admin\\Desktop\\test\\活跃号(实号)6.9MB 电信 张小盖.xlsx";


    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

    private static void read2() throws FileNotFoundException {
        FileInputStream in = new FileInputStream(FILE_PATH);
        Workbook wk = StreamingReader.builder()
                .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件

        System.out.println("sheets:" + wk.getNumberOfSheets());

        int last = wk.getSheetAt(0).getLastRowNum();
        System.out.println("last:" + last);
    }

    private static void read1(){
        try {
            Workbook wb = new XSSFWorkbook(OPCPackage.open(new FileInputStream(FILE_PATH)));
        } catch (Exception e) {
            throw new SystemWarnException(-1, "文件读取异常", e);
        }
    }
}
