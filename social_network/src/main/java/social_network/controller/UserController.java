package social_network.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	/**
	 * Получить страницу с пользователями
	 *
	 * @param filter   фильтр
	 * @param pageable настройки пагинации
	 * @return страница с пользователями
	 */
	@GetMapping
	public Page<UserByListDto> findAll(@RequestBody UserFilter filter, Pageable pageable) {
		return userService.findAll(filter, pageable);
	}
	
	/**
	 * Получить друзей пользователя по идентификатору
	 *
	 * @param id идентификатор пользователя
	 * @return страница с пользователями
	 */
	@GetMapping("{id}/friends")
	public Page<UserByListDto> findAllFriends(
			@PathVariable UUID id
			,@RequestBody FriendFilter filter
			, Pageable pageable
	) {
		log.info("Поиск пользователя по идентификатору id={}", id);
		return userService.findAllFriends(id, filter, pageable);
	}
	
	
	/**
	 * Получить пользователя по идентификатору
	 *
	 * @param id идентификатор пользователя
	 * @return DTO пользователя
	 */
	@GetMapping("{id}")
	public UserPageDto findOne(@PathVariable UUID id) {
		log.info("Поиск пользователя по идентификатору id={}", id);
		return userService.findOne(id);
	}
	
	/**
	 * Создать нового пользователя
	 *
	 * @param userDto данные пользователя в виде DTO
	 * @return response объект с соответствующим статусом состояния (200, 400, 409)
	 */
	@PostMapping
	public UUID create(@RequestBody @Valid UserRegistrationDto userDto) throws NoSuchFieldException {
		log.info("Создание нового пользователя user={}", userDto.toString());
		return userService.create(userDto);
	}
	
	/**
	 * Обновить данные существующего пользователя
	 *
	 * @param userDto данные пользователя в виде DTO
	 * @return response объект с соответствующим статусом состояния (200, 400, 409)
	 */
	@PutMapping("{id}")
	public UUID update(@PathVariable UUID id, @RequestBody @Valid UserEditDto userDto) {
		log.info("Изменение данных пользователя user={}", userDto.toString());
		return userService.update(id, userDto);
	}
	
	
	/**
	 * Добавить друга для существующего пользователя
	 *
	 * @param id       идентификатор пользователя
	 * @param friendId идентификатор друга
	 * @return response объект с соответствующим статусом состояния (200, 400, 409)
	 */
	@PutMapping("{id}/add-friend")
	public boolean addFriend(@PathVariable UUID id, @RequestParam UUID friendId) {
		log.info("Изменение данных пользователя user id ={}", id);
		return userService.addFriend(id, friendId);
	}
	
	/**
	 * Удалить пользователя
	 *
	 * @param id идентификатор пользователя
	 */
	@DeleteMapping("{id}")
	public void delete(@PathVariable UUID id) {
		log.info("Удаление данных пользователя user id ={}", id);
		userService.delete(id);
	}
	
	/**
	 * Удалить друга пользователя
	 *
	 * @param userId   идентификатор пользователя
	 * @param friendId идентификатор друга
	 */
	@DeleteMapping("{user-id}/delete-friend/{friend-id}")
	public void deleteFriend(@PathVariable("user-id") UUID userId, @PathVariable("friend-id") UUID friendId) {
		log.info("Удаление друга friendId ={} пользователя userId ={}", friendId, userId);
		userService.deleteFriend(userId, friendId);
	}
}