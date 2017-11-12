/**
 * 
 */
package fr.imie.supcrowdfunder.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author jerome
 *
 */
public class DateTimeAdapter extends XmlAdapter<String, DateTime>{
   private static DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

   @Override
   public DateTime unmarshal(String vt) throws Exception {
       return dtf.parseDateTime(vt);
   }

   @Override
   public String marshal(DateTime bt) throws Exception {
       return dtf.print(bt);

   }
}

