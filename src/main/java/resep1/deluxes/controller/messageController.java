package resep1.deluxes.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import resep1.deluxes.domain.message;
import resep1.deluxes.repo.messageRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController //ОЧЕНЬ ВАЖНО!!!!!!!!!!!!!
@RequestMapping("message")  //сам message КОГДА мы вводим message будет что происходить (messages)
public class messageController {
    private final messageRepo messageRepo;

    @Autowired
    public messageController(messageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


    @GetMapping //отображение на localhost при вводе localhost:8080/message
    public List<message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}") //если мы заходим на сайт и вводим ...message/1
    public message getOne(@PathVariable("id") message message) {
        return message;
    }


    @PostMapping //так тут вроде мы будем запрашивать новый message или добавлять не сильно понял кажется добавлять
    public message create(@RequestBody message message) {
        message.setCreateDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}") //обновление нового message
    public message update(@PathVariable("id") message messageFromDB, @RequestBody message message) {
        BeanUtils.copyProperties(message, messageFromDB, "id");

        return messageRepo.save(messageFromDB);
    }

    @DeleteMapping("{id}") //удаление.. наверное
    public void delete(@PathVariable("id") message message) {
        messageRepo.delete(message);
    }
}

