package fr.biblioc.bibliocreservation.mapper;

import fr.biblioc.bibliocreservation.custom.ReservationExpire;
import fr.biblioc.bibliocreservation.dto.ReservationExpireDto;
import org.mapstruct.Mapper;

/**
 * Mapper mapstruct de l'Entity au DTO et l'inverse
 */
@Mapper(componentModel = "spring")
public interface ReservationExpireMapper {

    ReservationExpireDto reservationExpireToReservationExpireDto(ReservationExpire reservationExpire);
    ReservationExpire reservationExpireDtoToReservationExpire(ReservationExpireDto reservationExpireDto);
}