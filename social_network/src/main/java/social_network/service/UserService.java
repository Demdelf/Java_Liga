package social_network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import social_network.domain.User;
import social_network.dto.UserByListDto;
import social_network.dto.UserEditDto;
import social_network.dto.UserPageDto;
import social_network.dto.UserRegistrationDto;
import social_network.repository.UserRepository;
import social_network.service.filters.FriendFilter;
import social_network.service.filters.UserFilter;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	/**
	 * Получение всех пользователей
	 *
	 * @param filter   фильтр
	 * @param pageable пагинация
	 * @return страница с пользователями
	 */
	public Page<UserByListDto> findAll(UserFilter filter, Pageable pageable) {
		return userRepository
				.findAll(filter.toSpecification(), pageable)
				.map(this::convertToUserByListDto);
	}
	
	/**
	 * Получение всех друзей пользователя
	 *
	 * @param userId   идентификатор пользователя
	 * @param filter   фильтр
	 * @param pageable пагинация
	 * @return страница с пользователями
	 */
	public Page<UserByListDto> findAllFriends(UUID userId, FriendFilter filter, Pageable pageable) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find user with id: " + userId));
		filter.setUser(user);
		return userRepository
				.findAll(filter.toSpecification(), pageable)
				.map(this::convertToUserByListDto);
	}
	
	/**
	 * Поиск пользователя по идентификатору
	 *
	 * @param id идентификатор пользователя
	 * @return DTO пользователя
	 */
	public UserPageDto findOne(UUID id) {
		return userRepository.findById(id)
				.map(this::convertToUserPageDto)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find user with id: " + id));
	}
	
	/**
	 * Создание пользователя и сохранение в БД
	 *
	 * @param userDto данные пользователя
	 */
	@Transactional
	public UUID create(UserRegistrationDto userDto) {
		User user = convertUserRegistrationDtoToUser(userDto);
		user = userRepository.save(user);
		
		return user.getId();
	}
	
	/**
	 * Проверка данных пользователя и внесение их в БД
	 *
	 * @param userDto данные пользователя
	 */
	@Transactional
	public void update(UUID id, UserEditDto userDto) {
		User entity = Optional.ofNullable(id)
				.flatMap(userRepository::findById)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find user with id: " + id));
		
		User user = convertUserEditDtoToUser(userDto, entity);
		userRepository.save(user);
	}
	
	
	/**
	 * Удаление пользователя из БД
	 *
	 * @param id идентификатор пользователя
	 */
	@Transactional
	public void delete(UUID id) {
		User user = Optional.ofNullable(id)
				.flatMap(userRepository::findById)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find user with id: " + id));
		userRepository.delete(user);
	}
	
	/**
	 * Добавление друга пользователя
	 *
	 * @param userId   идентификатор пользователя
	 * @param friendId идентификатор друга
	 * @return успешно ли добавили друга
	 */
	@Transactional
	public void addFriend(UUID userId, UUID friendId) {
		User user = Optional.ofNullable(userId)
				.flatMap(userRepository::findById)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find user with id: " + userId));
		User friend = Optional.ofNullable(friendId)
				.flatMap(userRepository::findById)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find friend with id: " + friendId));
		friend.getFriends().add(user);
		user.getFriends().add(friend);
	}
	
	/**
	 * Удаление друга пользователя
	 *
	 * @param userId   идентификатор пользователя
	 * @param friendId идентификатор друга
	 * @return успешно ли удалили друга
	 */
	@Transactional
	public void deleteFriend(UUID userId, UUID friendId) {
		User user = Optional.ofNullable(userId)
				.flatMap(userRepository::findById)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find user with id: " + userId));
		User friend = Optional.ofNullable(friendId)
				.flatMap(userRepository::findById)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find friend with id: " + friendId));
		friend.getFriends().remove(user);
		user.getFriends().remove(friend);
	}
	
	/**
	 * Преобразование DTO-сущности в {@link User}
	 *
	 * @param dto данные о пользователе
	 * @return пользователь
	 */
	public User convertUserRegistrationDtoToUser(UserRegistrationDto dto) {
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setAge(dto.getAge());
		
		return user;
	}
	
	/**
	 * Преобразование сущности {@link User} в DTO
	 *
	 * @param user пользователь
	 * @return DTO
	 */
	private UserByListDto convertToUserByListDto(User user) {
		UserByListDto dto = new UserByListDto();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setCity(user.getCity());
		
		return dto;
	}
	
	/**
	 * Преобразование сущности {@link User} в DTO
	 *
	 * @param user пользователь
	 * @return DTO
	 */
	public UserPageDto convertToUserPageDto(User user) {
		UserPageDto dto = new UserPageDto();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setCity(user.getCity());
		dto.setAge(user.getAge());
		dto.setInterest(user.getInterest());
		dto.setSex(user.getSex());
		
		return dto;
	}
	
	/**
	 * Преобразование DTO-сущности в {@link User}
	 *
	 * @param dto  данные о пользователе
	 * @param user
	 * @return пользователь
	 */
	private User convertUserEditDtoToUser(UserEditDto dto, User user) {
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setAge(dto.getAge());
		user.setCity(dto.getCity());
		user.setInterest(dto.getInterest());
		user.setSex(dto.getSex());
		
		return user;
	}
}
