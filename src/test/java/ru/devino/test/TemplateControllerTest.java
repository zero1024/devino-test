package ru.devino.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TemplateControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testValidation() throws Exception {
        assert match(null).getStatusCodeValue() == 400;
    }

    @Test
    public void testMatch() throws Exception {
        ResponseEntity<Map> res = match("OlegVash schet otkluchen ot SMSservice");
        assert res.getStatusCodeValue() == 200;
        assert (boolean) res.getBody().get("success");
        assert res.getBody().get("sourceText").equals("OlegVash schet otkluchen ot SMSservice");
        assert res.getBody().get("template").equals("%w+Vash schet otkluchen ot SMSservice");

        res = match("Новый emailОт кого-то Тема какая-то");
        assert res.getStatusCodeValue() == 200;
        assert (boolean) res.getBody().get("success");
        assert res.getBody().get("sourceText").equals("Новый emailОт кого-то Тема какая-то");
        assert res.getBody().get("template").equals("Новый emailОт %w+Тема %w+");

        res = match("Доброе утро олег! Ваш заказ №289 \"Фрукты\" готов к доставке Вы можете назначить дату и время доставки на пункт выдачи самостоятельно");
        assert res.getStatusCodeValue() == 200;
        assert (boolean) res.getBody().get("success");
        assert res.getBody().get("sourceText").equals("Доброе утро олег! Ваш заказ №289 \"Фрукты\" готов к доставке Вы можете назначить дату и время доставки на пункт выдачи самостоятельно");
        assert res.getBody().get("template").equals("%w+ Ваш заказ %w+ готов к доставке Вы можете назначить дату и время доставки на %w+ самостоятельно");
    }

    @Test
    public void testNotMatch() throws Exception {
        ResponseEntity<Map> res = match("OlegVash schet otkluchen ot  SMSservice");
        assert res.getStatusCodeValue() == 200;
        assert !(boolean) res.getBody().get("success");
        assert res.getBody().get("sourceText").equals("OlegVash schet otkluchen ot  SMSservice");
        assert res.getBody().get("template") == null;
    }

    private ResponseEntity<Map> match(String text) {
        return restTemplate.postForEntity("/match", singletonMap("text", text), Map.class);
    }


}
