import org.fofaviewer.bean.FofaBean;
import org.fofaviewer.utils.RequestHelper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public void getCommonName(){
        String cert="";
        Pattern pattern = Pattern.compile("CommonName: ([\\w|\\.]+)\n\n");
        Matcher matcher = pattern.matcher(cert);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }

    }

    public void getSerialNumber(){
        String cert="";
        Pattern pattern = Pattern.compile("Serial Number: (\\d+)\n");
        Matcher matcher = pattern.matcher(cert);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        FofaBean client = new FofaBean(properties.getProperty("email").trim(), properties.getProperty("key").trim());
        client.setSize(properties.getProperty("maxSize"));
        RequestHelper helper = RequestHelper.getInstance();
        String a = "domain=\"baidu.com\"";
        System.out.println(a);
        String requestUrl = client.getParam(null, false) + helper.encode(a);
        System.out.println(requestUrl);
        System.out.println(helper.getHTML(requestUrl, 3000,5000));
//        Test test = new Test();
//        test.getCommonName();
//        test.getSerialNumber();
    }
}
