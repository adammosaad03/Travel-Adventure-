package com.codecademy.plants.controllers;

import com.codecademy.plants.entities.Adventure;  
import com.codecademy.plants.repositories.AdventureRepository;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;  
import org.springframework.web.bind.annotation.*;  
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional; 

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

  private final AdventureRepository adventureRepository;

  public TravelAdventuresController(AdventureRepository adventureRepo) {
    this.adventureRepository = adventureRepo;
  }

  // Add controller methods below:
  @GetMapping()
  public Iterable<Adventure> getAll(){
   
   return this.adventureRepository.findAll();

  }
  @GetMapping("/traveladventures/bycountry/{country}")
  public Iterable<Adventure> getCountries(@PathVariable country){
   return this.adventureRepository.findByCountry(country);
        
  }
  @GetMapping("/traveladventures/bystate/{state}") 
  public Iterable<Adventure> getStates(@PathVariable state){
  return this.adventureRepository.findByState(state);
  }
  @PostMapping("/traveladventures")
  public void postAdventure(@RequestBody Adventure adventure){
    if(adventure != null){
      this.adventureRepository.save(adventure);
    }
    else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error adding the adventure");
    }
  }
    @PutMapping("/traveladventures/{id}")
    public Adventure putAdventure(@PathVariable int id, @RequestBody Adventure adventure){
      Optional<Adventure> oldAdventure = this.adventureRepository.findById(id)
      if(!oldAdventure.isPresent()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry but there was an error trying to find that one.")
      }
        adventure.setId(id);
        return this.adventureRepository.save(adventure);
    }

@DeleteMapping("/traveladventures/{id}")
  public void deleteAdv(@PathVariable int id){
    Optional<Adventure> adventure = this.adventureRepository.findById(id)
    if(!adventure.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry adventure not found.")
    }else{
    this.adventureRepository.deleteById(id);

    }

  }
  
  
  
  
  }




  
}
