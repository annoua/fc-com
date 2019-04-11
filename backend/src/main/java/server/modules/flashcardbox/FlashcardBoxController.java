package server.modules.flashcardbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import server.config.Config;
import server.entities.dto.RequestDTO;
import server.entities.dto.ResponseDTO;
import server.exceptions.FccExcpetion;
import server.modules.utils.StatusDTO;

@Controller
@RequestMapping("/flashcardbox")
public class FlashcardBoxController {

    private final FlashcardBoxService flashcardBoxService;

    @Autowired
    public FlashcardBoxController(FlashcardBoxService flashcardBoxService) {
        this.flashcardBoxService = flashcardBoxService;
    }

    @CrossOrigin(origins = Config.ORIGIN_URL)
    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public @ResponseBody
    ResponseDTO addBox(@RequestBody RequestDTO requestDTO) throws FccExcpetion {
        return flashcardBoxService.addBox(requestDTO);
    }

    @CrossOrigin(origins = Config.ORIGIN_URL)
    @RequestMapping(path = "/get", method = RequestMethod.POST)
    public @ResponseBody
    ResponseDTO getBox(@RequestBody RequestDTO requestDTO) throws FccExcpetion {
        return flashcardBoxService.getBox(requestDTO);
    }

    @CrossOrigin(origins = Config.ORIGIN_URL)
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public @ResponseBody
    ResponseDTO editBox(@RequestBody RequestDTO requestDTO) throws FccExcpetion {
        return flashcardBoxService.editBox(requestDTO);
    }

    @CrossOrigin(origins = Config.ORIGIN_URL)
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    ResponseDTO deleteBox(@RequestBody RequestDTO requestDTO) throws FccExcpetion {
        return flashcardBoxService.deleteBox(requestDTO);
    }
}