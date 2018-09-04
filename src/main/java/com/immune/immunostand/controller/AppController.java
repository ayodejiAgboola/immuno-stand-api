package com.immune.immunostand.controller;

import com.immune.immunostand.model.*;
import com.immune.immunostand.repository.HospitalRepository;
import com.immune.immunostand.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class AppController {
    @Autowired
    HospitalRepository hospitalRepository;

    private UserDao userDao;
    private final String CONTRACT_ADDRESS = "0xa9d5d3eed52560b4d55c050659bb5337a61d9ab0";


    public AppController() {
    }
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        String username = request.getUsername();
        String password = BCrypt.hashpw(request.getPassword(),BCrypt.gensalt());
        String email = request.getEmail();
        String userType = request.getUserType();
        String hospitalId = request.getHospitalId();
        User user = new User(username,password,email,"0",userType, hospitalId);
        userDao.save(user);
        return new RegisterResponse(userDao.findByUsername(username));
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpSession session){
        String username = request.getUsername();
        String password = request.getPassword();
        User user = userDao.findByUsername(username);
        boolean passwordIsCorrect = BCrypt.checkpw(password,user.getPassword());
        if(passwordIsCorrect){
            session.setAttribute("loginStatus", true);
            session.setAttribute("user",user.getId());
            return new LoginResponse(user);
        }else{
            return null;
        }
    }
    @RequestMapping(value="/hospital", method= RequestMethod.POST)
    public Hospital createHospital(@RequestBody Hospital hospital){
        hospital.generateId();
        return hospitalRepository.save(hospital);
    }

    @RequestMapping(value="/hospital", method= RequestMethod.GET)
    public List<Hospital> getHospital(){
        return (List<Hospital>) hospitalRepository.findAll();
    }

    @RequestMapping(value="/hospital/{id}", method= RequestMethod.GET)
    public Hospital getHospital(@PathVariable String id){
        return hospitalRepository.findById(id);
    }

    @RequestMapping(value="/child/{hospitalid}", method= RequestMethod.POST)
    public Child registerChild(@RequestBody Child child, @PathVariable String hospitalid) throws Exception {
        child.generateDetailsHash();
        Hospital hospital = hospitalRepository.findById(hospitalid);
        hospital.getChildren().add(child);
        hospitalRepository.save(hospital);
            return child;
    }

    @RequestMapping(value="/child/{hospitalId}/{hash}", method= RequestMethod.GET)
    public Child getChild(@PathVariable String hash, @PathVariable String hospitalId){
        Hospital hospital = hospitalRepository.findById(hospitalId);
        for (int i = 0; i<hospital.getChildren().size(); i++){
            if(hospital.getChildren().get(i).getDetailsHash().equals(hash)){
                return hospital.getChildren().get(i);
            }
        }
        throw new NoSuchElementException("Child doesn't exist");
    }

    @RequestMapping(value="/child/{hospitalId}/{hash}/{shedulecode}", method= RequestMethod.POST)
    public Child updateChild(@PathVariable String hash, @PathVariable String shedulecode, @RequestBody Child.Schedule schedule, @PathVariable String hospitalId)throws Exception{
        Child child = new Child();
        Hospital childHospital = hospitalRepository.findById(hospitalId);
        int code = Integer.valueOf(shedulecode);
        for (int i = 0; i<childHospital.getChildren().size(); i++){
            if(childHospital.getChildren().get(i).getDetailsHash().equals(hash)){
                child = childHospital.getChildren().get(i);
                for(int j=0; j<child.getScheduleList().size(); j++){
                    if(child.getScheduleList().get(j).getImmunizationCode()==code){
                        childHospital.getChildren().get(i).getScheduleList().get(j).setCountCompleted(schedule.getCountCompleted());
                        hospitalRepository.save(childHospital);
                    }
                }

            }
        }
        return child;
    }



}
