
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
/**
 * Standford Named Entity Demo
 * @author Ganesh
 */
public class standardTest
{
 /**
 * identify Name,organization location etc entities and return Map<List>
 * @param text -- data
 * @param model - Stanford model names out of the three models
 * @return
 */
 public static LinkedHashMap <String,LinkedHashSet<String>> identifyNER(String text,String model)
 {
 LinkedHashMap <String,LinkedHashSet<String>> map=new <String,LinkedHashSet<String>>LinkedHashMap();
 String serializedClassifier =model;
 System.out.println(serializedClassifier);
 CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
 List<List<CoreLabel>> classify = classifier.classify(text);
 for (List<CoreLabel> coreLabels : classify)
 {
 for (CoreLabel coreLabel : coreLabels)
 {
 
 String word = coreLabel.word();
 String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
// System.out.println(word +" "+category);
 if("LOCATION".equals(category) || "ORGANIZATION".equals(category)){
	 
 }else{
	 continue;
 }
 if(!"O".equals(category)  )
 {
 if(map.containsKey(category))
 {
 // key is already their just insert in arraylist
 map.get(category).add(word);
 }
 else
 {
 LinkedHashSet<String> temp=new LinkedHashSet<String>();
 temp.add(word);
 map.put(category,temp);
 }
 //System.out.println(word+":"+category);
 }
 
 }
 
 }
 Set set = map.entrySet();
 Iterator i = set.iterator();
 System.out.println("Printing Hashmap:");
 while(i.hasNext()){
	 Map.Entry me = (Map.Entry)i.next();
//	 if(me.getKey() == "ORGANIZATION"){
	 System.out.print(me.getKey() + ": ");
     System.out.println(me.getValue());
//     }
 }
 System.out.println("Done Printing!");
 return map;
 }
 public static void main(String args[])
 {
 String path1 = "/home/manikanta/Desktop/Resume Extractor/sample_resumes/akasha_resume.txt";
 BufferedReader br;
 String resume1 = "";
 try {
	br = new BufferedReader(new FileReader(path1));
	StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
    }
    resume1 = sb.toString();
    br.close();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
 
 identifyNER(resume1, "/home/manikanta/Desktop/Resume Extractor/stanford-ner-2014-01-04/classifiers/english.muc.7class.distsim.crf.ser.gz").toString();
 }
 
}