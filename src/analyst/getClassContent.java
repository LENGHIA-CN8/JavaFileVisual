package analyst;

import java.io.*;
import java.util.*;

public class getClassContent {
    protected File[] listOfFiles;

    public getClassContent (String path) throws IOException {
        //listOfFiles=new File[100];
        ListFileFromFolder(path);
    }

    public File[] ListFileFromFolder (String path) throws IOException {
        if(path.contains(".java")){
            File file= new File(path);
            listOfFiles=new File[1];
            listOfFiles[0]=file;
        } else {
            File folder = new File(path);

            FilenameFilter filter = (dir, name) -> {
                if (name.endsWith(".java")) {
                    return true;
                } else {
                    return false;
                }
            };

            listOfFiles = folder.listFiles(filter);
        }

        return listOfFiles;
    }
    public List<String> readContentFromFile(File file) throws IOException,NullPointerException {
        List<String> content=new ArrayList<>();
        String str;
        FileReader rd=new FileReader(file);
        BufferedReader in = new BufferedReader(rd);
        while((str=in.readLine())!=null) {
            if (!str.contains("//")) {
                StringTokenizer st = new StringTokenizer(str, " ");
                while (st.hasMoreTokens()) {
                    String s = st.nextToken();
                    if (s.contains("=") && !s.equals("=")) {
                        int idx1 = s.indexOf("=");
                        content.add(s.substring(0, idx1));
                        content.add("=");
                        if (s.contains(";")) {
                            int idx2 = s.indexOf(";");
                            content.add(s.substring(idx1 + 1, idx2));
                            //content.add(s.substring(idx2,s.length()));
                            content.add(";");
                        } else
                            content.add(s.substring(idx1 + 1, s.length()));
                    } else if (s.contains(";") && !s.equals(";")) {
                        int idx1 = s.indexOf(";");
                        content.add(s.substring(0, idx1));
                        content.add(";");
                        //content.add(s.substring(idx1 + 1, s.length()));
                    } else if (s.contains("(") && !s.equals("(")) {
                        int idx1 = s.indexOf("(");
                        if (idx1 > 0) {
                            content.add(s.substring(0, idx1));
                            content.add("(");
                        } else content.add("(");
                        if (s.contains(")")) {
                            int idx2 = s.indexOf(")");
                            while (idx1 > idx2) {
                                idx2 = s.indexOf(")", idx1);
                            }
                            content.add(s.substring(idx1 + 1, idx2));
                            content.add(")");
                            content.add(s.substring(idx2 + 1, s.length()));

                        } else content.add(s.substring(idx1 + 1, s.length()));
                    } else if (s.contains(")") && !s.equals(")")) {
                        int idx1 = s.indexOf(")");
                        content.add(s.substring(0, idx1));
                        content.add(")");
                        content.add(s.substring(idx1 + 1, s.length()));
                    } else if (s.contains(",") && !s.equals(",")) {
                        int idx1 = s.indexOf(",");
                        content.add(s.substring(0, idx1));
                        content.add(",");
                        String s1 = s.substring(idx1 + 1, s.length());
                        if (!s1.equals("")) {
                            content.add(s1);
                        }
                    } else {
                        content.add(s);
                    }
                }
            }
        }
        //System.out.println(content);
        return content;
    }

}
