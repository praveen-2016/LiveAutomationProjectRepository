package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateProvider
{
		
public String dateProvider()
{
return "nadda.kumar"+DateTimeFormatter.ofPattern("hh:mm:ss").format(LocalDateTime.now()).replace(':', '_')+"@gmail.com";
		
}

}
