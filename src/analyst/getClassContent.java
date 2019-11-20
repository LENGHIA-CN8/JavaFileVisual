package analyst;

import java.io.*;
import java.util.*;

public class getClassContent {
    protected File[] listOfFiles;

    public getClassContent (String path) throws IOException {
        listOfFiles=new File[100];
        ListFileFromFolder("");
    }

    public File[] ListFileFromFolder (String path) throws IOException {
        File folder = new File("/Users/user/Desktop/JavaFileVisual");
        FilenameFilter filter = (dir, name) -> {
            if (name.endsWith(".java")) {
                return true;
            } else {
                return false;
            }
        };

        listOfFiles=folder.listFiles(filter);
        //System.out.println(listOfFiles[0].getName());
//        for(int i=0;i<listOfFiles.length;i++){
//            readContentFromFile(listOfFiles[i]);
//        }
        return listOfFiles;
    }
    public List<String> readContentFromFile(File file) throws IOException {
        List<String> content=new ArrayList<>();
        String str;
        FileReader rd=new FileReader(file);
        BufferedReader in = new BufferedReader(rd);
        while((str=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer( str , " ");
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                if(s.contains("=")&&!s.equals("=")){
                    int idx1=s.indexOf("=");
                    content.add(s.substring(0,idx1));
                    content.add("=");
                    if(s.contains(";")){
                        int idx2=s.indexOf(";");
                        content.add(s.substring(idx1+1,idx2));
                        content.add(s.substring(idx2,s.length()));
                    } else
                    content.add(s.substring(idx1+1,s.length()));
                } else if (s.contains("(") && !s.equals("(")) {
                    int idx1 = s.indexOf("(");
                    if(idx1>0) {
                        content.add(s.substring(0, idx1));
                        content.add("(");
                    } else content.add("(");
                    if (s.contains(")")) {
                        int idx2 = s.indexOf(")");
                        content.add(s.substring(idx1 + 1, idx2));
                        content.add(")");
                        content.add(s.substring(idx2 + 1, s.length()));
                    } else content.add(s.substring(idx1 + 1, s.length()));
                } else if (s.contains(")") && !s.equals(")")) {
                    int idx1 = s.indexOf(")");
                    content.add(s.substring(0, idx1));
                    content.add(")");
                    content.add(s.substring(idx1 + 1, s.length()));
                } else if (s.contains(";") && !s.equals(";")) {
                    int idx1 = s.indexOf(";");
                    content.add(s.substring(0, idx1));
                    content.add(";");
                    content.add(s.substring(idx1 + 1, s.length()));
                } else if (s.contains(",") && !s.equals(",")) {
                    int idx1 = s.indexOf(",");
                    content.add(s.substring(0, idx1));
                    content.add(",");
                    content.add(s.substring(idx1 + 1, s.length()));
                } else {
                    content.add(s);
                }
            }
        }
        //System.out.println(content);
        return content;
    }
}
