package edu.lfa.scrapandcrawl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapAndCrawl {

    public static void main(String[] args) {
        try {
            BufferedReader re = new BufferedReader(new FileReader("G:\\filehandling\\Jobs\\programmingurls.txt"));
            int i=0;
            String link = "";
            while ((link = re.readLine()) != null) {

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = "";
                StringBuilder content = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");

                }
                               reader.close();
                String regExJobsNepal = "<a(.*?)>\\s*(.*?)\\s*</a>\\s*(.*?)/>\\s*</td>\\s*<td >\\s*(.*?)>\\s*(.*?)\\s*</a>";
                String regExJobsNepal1="<a(.*?)>\\s*(.*?)\\s*</a>\\s*</td>\\s*<td >\\s*(.*?)>\\s*(.*?)\\s*</a>";
                String regExMeroJobs="<h4 class(.*?)>(.*?)<(.*?)/h4>\\s*(.*?)>(.*?)</p>";
                Pattern pattern = Pattern.compile(regExJobsNepal);
                Matcher matcher = pattern.matcher(content);
                 Pattern patternMero = Pattern.compile(regExMeroJobs);
                Matcher matcherMero = patternMero.matcher(content);
                Pattern pattern1 = Pattern.compile(regExJobsNepal1);
                Matcher matcher1 = pattern1.matcher(content);
                System.out.println("***********************************************");
                while (matcher.find()) {
                    System.out.print(" Job Title:      " + matcher.group(2)+"========>           ");
                    System.out.println("Company Name:      "+matcher.group(5));
                }
                while (matcher1.find()) {
                    System.out.print(" Job Title:      " + matcher1.group(2)+"========>           ");
                    System.out.println("Company Name:      "+matcher1.group(4));
                }
                               while (matcherMero.find()) {
                    System.out.print(" Job Title:      " + matcherMero.group(2)+"========>           ");
                    System.out.println("Company Name:      "+matcherMero.group(5));
                }
            }
            re.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
