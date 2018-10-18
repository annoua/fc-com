package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import server.entities.dto.FlashCardDTO;
import server.services.FlashCardService;

@Controller
@RequestMapping("/flashcard")
public class FlashCardController {

    private FlashCardService flashCardService;

    @Autowired
    public void setFlashCardService(FlashCardService flashCardService) {
        this.flashCardService = flashCardService;
    }


    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET)
    public @ResponseBody
    FlashCardDTO getFlashCardWithID(@PathVariable("id") String id_s) {
        return flashCardService.getFlashCardWithID(id_s);
    }
}
