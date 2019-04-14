package ro.fiipractic.mycinema.scheduledtasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.fiipractic.mycinema.dto.MailData;
import ro.fiipractic.mycinema.services.EmailService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScheduledEmail {
    private Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private EmailService emailService;

    @Autowired
    public ScheduledEmail(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 51 23 * * ?")
    public void testing() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        MailData mailData = new MailData();
        mailData.setFrom("estrazionefatture2019@gmail.com");
        mailData.setTo("sergiu.chiuchiu@gmail.com");
        mailData.setSubject("Test Email");

        Map<String, Object> model = new HashMap<>();
        model.put("Name", "Andrei B");
        model.put("location", "NYC");

        emailService.sendEmail(mailData, model);


    }

}
