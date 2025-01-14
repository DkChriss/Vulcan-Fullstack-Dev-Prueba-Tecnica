package com.vulcan.dev_test.domain.user.rest;

import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.rest.request.StudentDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentStoreDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentUpdateDto;
import com.vulcan.dev_test.domain.user.User;
import com.vulcan.dev_test.domain.user.rest.mapper.UserMapper;
import com.vulcan.dev_test.domain.user.rest.request.UserDto;
import com.vulcan.dev_test.domain.user.rest.request.UserStoreDto;
import com.vulcan.dev_test.domain.user.rest.request.UserUpdateDto;
import com.vulcan.dev_test.domain.user.service.UserService;
import com.vulcan.dev_test.handler.response.GlobalResponseEntity;
import com.vulcan.dev_test.handler.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse<Page<UserDto>>> list (
            @RequestParam(required = false, defaultValue = "", value = "name") String name,
            @PageableDefault Pageable pageable
    ) {
        Integer size = userService.size();
        List<UserDto> userDtoList = userService.list(
                        name == null ? "" : name,
                        pageable.getPageNumber(),
                        pageable.getPageSize())
                .getContent()
                .stream()
                .map(userMapper::toDto)
                .toList();
        Page<UserDto> response = new PageImpl<>(userDtoList, pageable, size);
        return GlobalResponseEntity.successResponse(
                "Se ha obtenido la lista de usuarios correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<UserDto>> store(
            @RequestBody @Valid UserStoreDto userStoreDto
    ) {
        User user = userMapper.toEntity(userStoreDto);
        UserDto response = userMapper.toDto(userService.store(user));

        return GlobalResponseEntity.successResponse(
                "Se ha registrado el usuario correctamente",
                "1",
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<UserDto>> show(@PathVariable("id") Long id) {
        UserDto response = userMapper.toDto(userService.show(id));
        return GlobalResponseEntity.successResponse(
                "Se ha obtenido el usuario correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessResponse<UserDto>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UserUpdateDto userUpdateDto
    ) {
        userUpdateDto.setId(id);
        User user = userMapper.toEntity(userUpdateDto);
        UserDto response = userMapper.toDto(userService.update(user));

        return GlobalResponseEntity.successResponse(
                "Se ha actualizado los datos del usuario correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse<UserDto>> destroy(@PathVariable("id") Long id) {
        this.userService.destroy(id);
        return GlobalResponseEntity.successResponse(
                "Se ha eliminado el usuario correctamente",
                "1",
                null,
                HttpStatus.OK
        );
    }
}
