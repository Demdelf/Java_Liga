package social_network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social_network.dto.UserByListDto;
import social_network.dto.UserEditDto;
import social_network.dto.UserPageDto;
import social_network.dto.UserRegistrationDto;
import social_network.service.UserService;
import social_network.service.filters.FriendFilter;
import social_network.service.filters.UserFilter;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер для работы с пользователем
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	/**
	 * Получить страницу с пользователями с возможностью фильтрации
	 *
	 * @param filter   фильтр
	 * @param pageable настройки пагинации
	 * @return страница с пользователями
	 */
	@GetMapping
	public Page<UserByListDto> getAllUsers(UserFilter filter, @PageableDefault(size = 3) Pageable pageable) {
		return userService.findAll(filter, pageable);
	}
	
	/**
	 * Получить друзей(с возможностью фильтрации) пользователя по идентификатору
	 *
	 * @param id идентификатор пользователя
	 * @return страница с пользователями
	 */
	@GetMapping("{id}/friends")
	public Page<UserByListDto> getAllFriends(
			@PathVariable UUID id
			,@RequestBody FriendFilter filter
			, Pageable pageable
	) {
		return userService.findAllFriends(id, filter, pageable);
	}
	
	/**
	 * Получить пользователя по идентификатору
	 *
	 * @param id идентификатор пользователя
	 * @return DTO пользователя
	 */
	@GetMapping("{id}")
	public UserPageDto get(@PathVariable UUID id) {
		return userService.findOne(id);
	}
	
	/**
	 * Создать нового пользователя
	 *
	 * @param userDto данные пользователя в виде DTO
	 * @return response объект
	 */
	@PostMapping
	public ResponseEntity<UUID> create(@RequestBody @Valid UserRegistrationDto userDto) {
		UUID id = userService.create(userDto);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	/**
	 * Обновить данные существующего пользователя
	 *
	 * @param userDto данные пользователя в виде DTO
	 * @return response объект с соответствующим статусом состояния (200, 400, 404)
	 */
	@PutMapping("{id}")
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid UserEditDto userDto) {
		userService.update(id, userDto);
		return new ResponseEntity<>("User was updated, id: " + id, HttpStatus.OK);
	}
	
	
	/**
	 * Добавить друга для существующего пользователя
	 *
	 * @param id       идентификатор пользователя
	 * @param friendId идентификатор друга
	 * @return response объект
	 */
	@PostMapping("add-friend")
	public ResponseEntity<String> addFriend(@RequestParam UUID id, @RequestParam UUID friendId) {
		userService.addFriend(id, friendId);
		return new ResponseEntity<>("Friend was added", HttpStatus.OK);
	}
	
	/**
	 * Удалить пользователя
	 *
	 * @param id идентификатор пользователя
	 */
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam UUID id) {
		userService.delete(id);
		return new ResponseEntity<>("User was deleted", HttpStatus.OK);
	}
	
	/**
	 * Удалить друга пользователя
	 *
	 * @param userId   идентификатор пользователя
	 * @param friendId идентификатор друга
	 */
	@DeleteMapping("delete-friend")
	public ResponseEntity<String> deleteFriend(@RequestParam UUID userId, @RequestParam UUID friendId) {
		userService.deleteFriend(userId, friendId);
		return new ResponseEntity<>("Friend was deleted", HttpStatus.OK);
	}
}