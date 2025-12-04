package pccth.sp.pccthspseedservice.utils.lexto;
/**
 * Licensed under the CC-GNU Lesser General Public License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://creativecommons.org/licenses/LGPL/2.1/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
// LongLexTo: Tokenizing Thai texts using Longest Matching Approach
//   Note: Types: 0=unknown  1=known  2=ambiguous  3=English/digits  4=special characters
//
// Public methods: 
//   1) public LongLexTo(File dictFile);	//Constructor with a dictionary file
//   2) public void addDict(File dictFile);     //Add dictionary (e.g., unknown-word file)
//   3) public void wordInstance(String text);  //Word tokenization
//   4) public void lineInstance(String text);  //Line-break tokenization 
//   4) public Vector getIndexList();
//   5) Iterator's public methods: hasNext, first, next
//
// Author: Choochart Haruechaiyasak
// Last update: 28 March 2006

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Vector;

@Slf4j
public class LongLexTo {

    //Private variables
    private static final Trie dict = new Trie();               //For storing words from dictionary
    private LongParseTree ptree;     //Parsing tree (for Thai words)

    //Returned variables
    private Vector indexList;  //List of word index positions
    private Vector lineList;   //List of line index positions
    private Vector typeList;   //List of word types (for word only)
    private Iterator iter;     //Iterator for indexList OR lineList (depends on the call)

    /*******************************************************************/
    /*********************** Return index list *************************/
    /*******************************************************************/
    public Vector getIndexList() {
        return indexList;
    }

    /*******************************************************************/
    /*********************** Return type list *************************/
    /*******************************************************************/
    public Vector getTypeList() {
        return typeList;
    }

    /*******************************************************************/
    /******************** Iterator for index list **********************/
    /*******************************************************************/
    //Return iterator's hasNext for index list
    public boolean hasNext() {
        if (!iter.hasNext())
            return false;
        return true;
    }

    //Return iterator's first index
    public int first() {
        return 0;
    }

    //Return iterator's next index
    public int next() {
        return ((Integer) iter.next()).intValue();
    }

    static {
        try {
            String line;
            try (InputStream is = LongLexTo.class.getClassLoader().getResourceAsStream("lexitron.txt");
                 BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.length() > 0)
                        dict.add(line);
                }
            }
        } catch (Exception e) {
        	e.getMessage();
            //log.error("Error LongLexTo " + e.getMessage(), e);
        }
    }

    /*******************************************************************/
    /********************** Constructor (default) **********************/
    /*******************************************************************/
    public LongLexTo() throws IOException {

//      dict = new Trie();
//    File dictFile=new File("C:\\Users\\chuchart\\Downloads\\lexitron.txt");
//      File dictFile=new FileInputStream("lexitron.txt");
        //  ClassLoader cl = LongLexTo.class.getClassLoader();
        // File dictFile =cl.getResource("lexitron.txt").getFile();
//    		new ClassPathResource("com/report/util/lexitron.txt").getFile();
//    if(dictFile.exists())
//      addDict(dictFile);
//    else
//      System.out.println(" !!! Error: Missing default dictionary file, lexitron.txt");
//      addDict(new File(""));
        indexList = new Vector();
        lineList = new Vector();
        typeList = new Vector();
        ptree = new LongParseTree(dict, indexList, typeList);
    } //Constructor

    /*******************************************************************/
    /************** Constructor (passing dictionary file ) *************/
    /*******************************************************************/
    public LongLexTo(File dictFile) throws IOException {

//    dict=new Trie();
        if (dictFile.exists())
            addDict(dictFile);
        else
            System.out.println(" !!! Error: The dictionary file is not found, " + dictFile.getName());
        indexList = new Vector();
        lineList = new Vector();
        typeList = new Vector();
        ptree = new LongParseTree(dict, indexList, typeList);
    } //Constructor

    /*******************************************************************/
    /**************************** addDict ******************************/
    /*******************************************************************/
    public void addDict(File dictFile) throws IOException {

        //Read words from dictionary
        String line, word, word2;
        int index;

//    FileInputStream fis = new FileInputStream(dictFile);
//    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//
//
//  //  FileReader fr = new FileReader(dictFile);
//    BufferedReader br = new BufferedReader(isr);
//
//    while((line=br.readLine())!=null) {
//      line=line.trim();
//      if(line.length()>0)
//        dict.add(line);
//    }

//      InputStream is = LongLexTo.class.getClassLoader().getResourceAsStream("lexitron.txt");

//      try (Scanner scanner = new Scanner(is)) {
//          while (scanner.hasNextLine()) {
//              line = scanner.nextLine();
//              if (line.length() > 1) {
//                  dict.add(line);
//              }
//          }
//      }

      try (InputStream is = LongLexTo.class.getClassLoader().getResourceAsStream("lexitron.txt");
           BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
          while ((line = br.readLine()) != null) {
              line = line.trim();
              if (line.length() > 0)
                  dict.add(line);
          }
      }

    } //addDict
  
  /****************************************************************/
  /************************** wordInstance ************************/
  /****************************************************************/
  public void wordInstance(String text) {

      indexList.clear();
      typeList.clear();
      int pos, index;
      String word;
      boolean found;
      char ch;

      pos = 0;
      while (pos < text.length()) {

          //Check for special characters and English words/numbers
          ch = text.charAt(pos);

          //English
          if (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))) {
              while ((pos < text.length()) && (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))))
                  ch = text.charAt(pos++);
              if (pos < text.length())
                  pos--;
              indexList.addElement(new Integer(pos));
              typeList.addElement(new Integer(3));
          }
          //Digits
          else if (((ch >= '0') && (ch <= '9')) || ((ch >= '๐') && (ch <= '๙'))) {
              while ((pos < text.length()) && (((ch >= '0') && (ch <= '9')) || ((ch >= '๐') && (ch <= '๙')) || (ch == ',') || (ch == '.')))
                  ch = text.charAt(pos++);
              if (pos < text.length())
                  pos--;
              indexList.addElement(new Integer(pos));
              typeList.addElement(new Integer(3));
          }
          //Special characters
          else if ((ch <= '~') || (ch == 'ๆ') || (ch == 'ฯ') || (ch == '“') || (ch == '”') || (ch == ',')) {
              pos++;
              indexList.addElement(new Integer(pos));
              typeList.addElement(new Integer(4));
          }
          //Thai word (known/unknown/ambiguous)
          else
              pos = ptree.parseWordInstance(pos, text);
      } //While all text length
      iter = indexList.iterator();
  } //wordInstance

    /****************************************************************/
    /************************** lineInstance ************************/
    /****************************************************************/
//    public void lineInstance(String text) {
//
//        int windowSize = 10; //for detecting parentheses, quotes
//        int curType, nextType, tempType, curIndex, nextIndex, tempIndex;
//        lineList.clear();
//        wordInstance(text);
//        int i;
//        for (i = 0; i < typeList.size() - 1; i++) {
//            curType = ((Integer) typeList.elementAt(i)).intValue();
//            curIndex = ((Integer) indexList.elementAt(i)).intValue();
//
//            if ((curType == 3) || (curType == 4)) {
//                //Parenthesese
//                if ((curType == 4) && (text.charAt(curIndex - 1) == '(')) {
//                    int pos = i + 1;
//                    while ((pos < typeList.size()) && (pos < i + windowSize)) {
//                        tempType = ((Integer) typeList.elementAt(pos)).intValue();
//                        tempIndex = ((Integer) indexList.elementAt(pos++)).intValue();
//                        if ((tempType == 4) && (text.charAt(tempIndex - 1) == ')')) {
//                            lineList.addElement(new Integer(tempIndex));
//                            i = pos - 1;
//                            break;
//                        }
//                    }
//                }
//                //Single quote
//                else if ((curType == 4) && (text.charAt(curIndex - 1) == '\'')) {
//                    int pos = i + 1;
//                    while ((pos < typeList.size()) && (pos < i + windowSize)) {
//                        tempType = ((Integer) typeList.elementAt(pos)).intValue();
//                        tempIndex = ((Integer) indexList.elementAt(pos++)).intValue();
//                        if ((tempType == 4) && (text.charAt(tempIndex - 1) == '\'')) {
//                            lineList.addElement(new Integer(tempIndex));
//                            i = pos - 1;
//                            break;
//                        }
//                    }
//                }
//                //Double quote
//                else if ((curType == 4) && (text.charAt(curIndex - 1) == '\"')) {
//                    int pos = i + 1;
//                    while ((pos < typeList.size()) && (pos < i + windowSize)) {
//                        tempType = ((Integer) typeList.elementAt(pos)).intValue();
//                        tempIndex = ((Integer) indexList.elementAt(pos++)).intValue();
//                        if ((tempType == 4) && (text.charAt(tempIndex - 1) == '\"')) {
//                            lineList.addElement(new Integer(tempIndex));
//                            i = pos - 1;
//                            break;
//                        }
//                    }
//                } else
//                    lineList.addElement(new Integer(curIndex));
//            } else {
//                nextType = ((Integer) typeList.elementAt(i + 1)).intValue();
//                nextIndex = ((Integer) indexList.elementAt(i + 1)).intValue();
//                if ((nextType == 3) ||
//                        ((nextType == 4) && ((text.charAt(nextIndex - 1) == ' ') || (text.charAt(nextIndex - 1) == '\"') ||
//                                (text.charAt(nextIndex - 1) == '(') || (text.charAt(nextIndex - 1) == '\''))))
//                    lineList.addElement(new Integer(((Integer) indexList.elementAt(i)).intValue()));
//                else if ((curType == 1) && (nextType != 0) && (nextType != 4))
//                    lineList.addElement(new Integer(((Integer) indexList.elementAt(i)).intValue()));
//            }
//        }
//        if (i < typeList.size())
//            lineList.addElement(new Integer(((Integer) indexList.elementAt(indexList.size() - 1)).intValue()));
//        iter = lineList.iterator();
//    } //lineInstance

    public int getSentenceLength(String text, String fontName, int fontSize) {
        //String text = "Hello World";
        Font font = new Font("TH Sarabun New", Font.PLAIN, 14);
        AffineTransform at = new AffineTransform();
        at.scale(0.5, 0.1);
        FontRenderContext frc = new FontRenderContext(at, true, true);

        int textwidth = (int) (font.getStringBounds(text, frc).getWidth());
//        int textheight = (int) (font.getStringBounds(text, frc).getHeight());


        return textwidth;
    }

    public JSONArray buildTextLinesByChars(String rawString, int lineLength) throws IOException {
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        String content = new String(bytes, StandardCharsets.UTF_8);
        JSONArray jsons = new JSONArray();
        // int lineLength = 358;
        int lineSize = 0;
        int begin, end, type;
        Vector typeList;
        StringBuilder buffer = new StringBuilder();
        // LongLexTo tokenizer=new LongLexTo(lexitron);
        wordInstance(content);
        typeList = getTypeList();
        begin = first();
        int i = 0;
        while (hasNext()) {
            end = next();

            int size = ThaiDisplayUtils.countCharsNoUpperLower(content.substring(begin, end));
            if (content.substring(begin, end).equalsIgnoreCase("\r")) {
                begin = end;
                continue;

            } else if (content.substring(begin, end).equalsIgnoreCase("\n")) {
                jsons.put(buffer.toString());
                buffer = new StringBuilder();
                // buffer.append(line.substring(begin, end));
                lineSize = 0;
            } else if (lineSize + size > lineLength) {
                jsons.put(buffer.toString());
                buffer = new StringBuilder();
                buffer.append(content.substring(begin, end));
                lineSize = size;
            } else {
                buffer.append(content.substring(begin, end));
                lineSize += size;
            }


            begin = end;
        }
        jsons.put(buffer.toString());
        return jsons;
    }

    public JSONArray buildTextLines(String rawString, int lineLength, String fontName, int fontSize) throws IOException {
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        String content = new String(bytes, StandardCharsets.UTF_8);
        JSONArray jsons = new JSONArray();
        // int lineLength = 358;
        int lineSize = 0;
        int begin, end, type;
        Vector typeList;
        StringBuilder buffer = new StringBuilder();
        // LongLexTo tokenizer=new LongLexTo(lexitron);
        wordInstance(content);
        typeList = getTypeList();
        begin = first();
        int i = 0;
        while (hasNext()) {
            end = next();
         /*        type=((Integer)typeList.elementAt(i++)).intValue();
       if(type==0)
        	 buffer.append("<font color=#ff0000>" + line.substring(begin, end) + "</font>");
         else if(type==1)
        	 buffer.append("<font color=#00bb00>" + line.substring(begin, end) + "</font>");
         else if(type==2)
        	 buffer.append("<font color=#0000bb>" + line.substring(begin, end) + "</font>");
         else if(type==3)
        	 buffer.append("<font color=#aa00aa>" + line.substring(begin, end) + "</font>");
         else if(type==4)
        	 buffer.append("<font color=#00aaaa>" + line.substring(begin, end) + "</font>");
    */
            int size = getSentenceLength(content.substring(begin, end), fontName, fontSize);
            if (content.substring(begin, end).equalsIgnoreCase("\r")) {
                begin = end;
                continue;

            } else if (content.substring(begin, end).equalsIgnoreCase("\n")) {
                jsons.put(buffer.toString());
                buffer = new StringBuilder();
                // buffer.append(line.substring(begin, end));
                lineSize = 0;
            } else if (lineSize + size > lineLength) {
                jsons.put(buffer.toString());
                buffer = new StringBuilder();
                buffer.append(content.substring(begin, end));
                lineSize = size;
            } else {
                buffer.append(content.substring(begin, end));
                lineSize += size;
            }


            begin = end;
        }
        jsons.put(buffer.toString());
//        return jsons.toString();
        return jsons;
    }
    /****************************************************************/
    /*************************** Demo *******************************/
    /****************************************************************/
//  public static void main(String[] args) throws IOException {
//      //  System.out.println(buildTextLines("ส่งมอบงานงวดที่ 4 (สี่) รายละเอียดงานที่ต้องส่งมอบปรากฏตามเอกสารแนบท้ายสัญญาผนวก 2 \r\n" +
//      //  		"โครงการพัฒนาระบบงานภาษีมูลค่าเพิ่มอย่างง่ายสำหรับการให้บริการทางอิเล็กทรอนิกส์ จากต่างประเทศ (Simplified VAT System for e-Service หรือ SVE)"));
//      LongLexTo tokenizer = new LongLexTo();
//      String rawString = "ส่งมอบงานงวดที่ 4 (สี่) รายละเอียดงานที่ต้องส่งมอบปรากฏตามเอกสารแนบท้ายสัญญาผนวก 2 \r\nโครงการพัฒนาระบบงานภาษีมูลค่าเพิ่มอย่างง่ายสำหรับการให้บริการทางอิเล็กทรอนิกส์ จากต่างประเทศ (Simplified VAT System for e-Service หรือ SVE)";
//      byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
//
//      String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
//      //    System.out.println("length-->"+ThaiDisplayUtils.countUpperLower(utf8EncodedString)+"len="+tokenizer.getSentenceLength(utf8EncodedString));
//      System.out.println(tokenizer.buildTextLines(utf8EncodedString, 358, "TH Sarabun New", 14));
//
//
///*
//    LongLexTo tokenizer=new LongLexTo(new File("C:\\Users\\chuchart\\Downloads\\lexitron.txt"));
//    File unknownFile=new File("unknown.txt");
//    if(unknownFile.exists())
//      tokenizer.addDict(unknownFile);
//    Vector typeList;
//    String text="", line, inFileName, outFileName;
//    char ch;
//    int begin, end, type;
//
//    File inFile, outFile;
//    FileReader fr;
//    BufferedReader br;
//    FileWriter fw;
//
//    BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));
//
//    System.out.println("\n\n*******************************"+System.getProperty("user.dir") );
//    System.out.println("*** LexTo: Lexeme Tokenizer ***");
//    System.out.println("*******************************"+tokenizer.getSentenceLength("ทำที่ญี่ปุ่น"));
//
//
//    String rawString = "ทำที่ญี่ปุ่น";
//    byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
//
//    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
//
//
//    String thaiStr = ThaiDisplayUtils.toDisplayString("ทำที่ญี่ปุ่น");
//    System.out.println("*******************************"+tokenizer.getSentenceLength("ทำที่ญี่ปุ่น"));
//    System.out.println("length-->"+ThaiDisplayUtils.countUpperLower(utf8EncodedString)+"len="+(utf8EncodedString).length());
//    do {
//      //Get input file name
//      do {
//      	System.out.print("\n >>> Enter input file ('q' to quit): ");
//        inFileName=(streamReader.readLine()).trim();
//        if(inFileName.equals("q"))
//          System.exit(1);
//        inFile=new File(System.getProperty("user.dir") + "//" + inFileName);
//      } while(!inFile.exists());
//
//      //Get output file name
//      System.out.print(" >>> Enter output file (.html only): ");
//      outFileName=(streamReader.readLine()).trim();
//      outFile=new File(System.getProperty("user.dir") + "//" + outFileName);
//
//      fr=new FileReader(inFile);
//      br=new BufferedReader(fr);
//      fw=new FileWriter(outFile);
//
//      while((line=br.readLine())!=null) {
//        line=line.trim();
//        if(line.length()>0) {
//
//          fw.write("<b>Text:</b> " + line);
//          fw.write("<br>\n");
//
//          fw.write("<b>Word instance:</b> ");
//          tokenizer.wordInstance(line);
//          typeList=tokenizer.getTypeList();
//          begin=tokenizer.first();
//          int i=0;
//          while(tokenizer.hasNext()) {
//            end=tokenizer.next();
//            type=((Integer)typeList.elementAt(i++)).intValue();
//            if(type==0)
//              fw.write("<font color=#ff0000>" + line.substring(begin, end) + "</font>");
//            else if(type==1)
//              fw.write("<font color=#00bb00>" + line.substring(begin, end) + "</font>");
//            else if(type==2)
//              fw.write("<font color=#0000bb>" + line.substring(begin, end) + "</font>");
//            else if(type==3)
//              fw.write("<font color=#aa00aa>" + line.substring(begin, end) + "</font>");
//            else if(type==4)
//              fw.write("<font color=#00aaaa>" + line.substring(begin, end) + "</font>");
//            fw.write("<font color=#000000>|</font>");
//            begin=end;
//          }
//          fw.write("<br>\n");
//
//          fw.write("<b>Line instance:</b> ");
//          tokenizer.lineInstance(line);
//          begin=tokenizer.first();
//          while(tokenizer.hasNext()) {
//            end=tokenizer.next();
//            fw.write(line.substring(begin, end) + "<font color=#ff0000>|</font>");
//            begin=end;
//          }
//          fw.write("<br><br>\n");
//        }
//      } //while all line
//      fw.write("<hr>");
//      fw.write("<font color=#ff0000>unknown</font> | ");
//      fw.write("<font color=#00bb00>known</font> | ");
//      fw.write("<font color=#0000bb>ambiguous</font> | ");
//      fw.write("<font color=#a00aa>English/Digits</font> | ");
//      fw.write("<font color=#00aaaa>special</font>\n");
//      fr.close();
//      fw.close();
//      System.out.println("\n *** Status: Use Web browser to view result: " + outFileName);
//    } while(true);
//    */
//  } //main
}
