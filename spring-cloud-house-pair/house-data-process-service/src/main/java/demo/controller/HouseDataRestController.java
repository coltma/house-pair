package demo.controller;

import demo.model.HouseData;
import demo.service.HouseDataProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class HouseDataRestController {

    @Autowired
    private HouseDataProcessService service;

    @RequestMapping(value = "/houses", method = RequestMethod.GET)
    public List<HouseData> findByLocation(@RequestParam(name = "lat") double lat,
                                          @RequestParam(name = "lng") double lng,
                                          @RequestParam(name = "radius") double radius){
        log.info(String.format("REST Request GET: [lat=%f], [lng=%f], [radius=%f]", lat, lng, radius));
        return this.service.findByLocationWithin(lat, lng, radius);
    }

    @RequestMapping(value = "/house/{id}", method = RequestMethod.GET)
    public HouseData findByLocation(@PathVariable  String id){
        log.info(String.format("REST Request GET: [id=%s]", id));
        return this.service.findById(id);
    }
}
