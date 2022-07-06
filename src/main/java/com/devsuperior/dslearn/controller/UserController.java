package com.devsuperior.dslearn.controller;

import com.devsuperior.dslearn.dto.UserDto;
import com.devsuperior.dslearn.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }
//
//    @PostMapping
//    public ResponseEntity<UserDto> insert(@Valid @RequestBody UserInsertDto userInsertDto){
//        UserDto userDto = userService.insert(userInsertDto);
//        URI uri = ServletUriComponentsBuilder.
//                fromCurrentRequest().
//                path("/{id}").
//                buildAndExpand(userDto.getId()).
//                toUri();
//        return ResponseEntity.created(uri).body(userDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
//        userService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateById(@PathVariable Long id, @Valid @RequestBody UserUpdateDto dto){
//        return ResponseEntity.ok(userService.updateById(id, dto));
//    }

}
