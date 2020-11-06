package social_network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
     * @param userId идентификатор пользователя
     * @param filter   фильтр
     * @param pageable пагинация
     * @return страница с пользователями
     */
    public Page<UserByListDto> findAllFriends(UUID userId, FriendFilter filter, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Создание пользователя и сохранение в БД
     *
     * @param userDto данные пользователя
     */
    @Transactional
    public UUID create(UserRegistrationDto userDto) {
        User user = convertUserRegistrationDtoToUser(userDto, new User());
        user = userRepository.save(user);

        return user.getId();
    }

    /**
     * Проверка данных пользователя и внесение их в БД
     *
     * @param userDto данные пользователя
     */
    @Transactional
    public UUID update(UUID id, UserEditDto userDto) {
        User entity = Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User user = convertUserEditDtoToUser(userDto, entity);
        user = userRepository.save(user);

        return user.getId();
    }


    /**
     * Удаление пользователя из БД
     * @param id идентификатор пользователя
     */
    @Transactional
    public void delete(UUID id) {
        User user = Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    /**
     * Добавление друга пользователя
     * @param userId идентификатор пользователя
     * @param friendId идентификатор друга
     * @return успешно ли добавили друга
     */
    @Transactional
    public boolean addFriend(UUID userId, UUID friendId) {
        User user = Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = Optional.ofNullable(friendId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Friend not found"));
        friend.getFriends().add(user);
        return user.getFriends().add(friend);
    }

    /**
     * Удаление друга пользователя
     * @param userId идентификатор пользователя
     * @param friendId идентификатор друга
     * @return успешно ли удалили друга
     */
    @Transactional
    public boolean deleteFriend(UUID userId, UUID friendId) {
        User user = Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = Optional.ofNullable(friendId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Friend not found"));
        friend.getFriends().remove(user);
        return user.getFriends().remove(friend);
    }

    /**
     * Преобразование DTO-сущности в {@link User}
     *
     * @param dto данные о пользователе
     * @return пользователь
     */
    public User convertUserRegistrationDtoToUser(UserRegistrationDto dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCity(dto.getCity());
        user.setAge(dto.getAge());
        user.setInterest(dto.getInterest());
        user.setSex(dto.getSex());

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
    private UserPageDto convertToUserPageDto(User user) {
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
     * @param dto данные о пользователе
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
