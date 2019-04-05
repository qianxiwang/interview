package classloader;

import java.io.*;

/**
 * 自定义的classloader：使用自定义的classloader到指定的目录下面的文件中加载
 */
public class PKClassLoader extends ClassLoader {

    private String path;
    private String classloadername;

    public PKClassLoader(String path, String classloadername) {
        this.path = path;
        this.classloadername = classloadername;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    //name:PK   根据不同的需求，自定义不同的这个方法
    private byte[] loadClassData(String name) {

        String fileName = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            in = new FileInputStream(new File(fileName));
            out = new ByteArrayOutputStream();
            int i = 0;

            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return out.toByteArray();
    }
}
