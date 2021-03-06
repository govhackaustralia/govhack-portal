package org.govhack.portal.web.controller;

import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.UserRepository;
import org.govhack.portal.data.view.UserView;
import org.govhack.portal.security.Authenticated;
import org.govhack.portal.service.UserService;
import org.govhack.portal.web.model.UserCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = {"/api/user"})
public class UserApiController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    UserApiController() {
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ResponseEntity<UserView> createUser(@RequestBody UserCreateModel model) {
        User x = userService.create(model);
        return new ResponseEntity<>(new UserView(x), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<UserView> userDetails(@Authenticated User user) {
        return new ResponseEntity<>(new UserView(user), HttpStatus.OK);
    }

}
