package fr.biblioc.bibliocreservation.mapper;

import fr.biblioc.bibliocreservation.dto.PreReservationDto;
import fr.biblioc.bibliocreservation.model.PreReservation;
import org.mapstruct.Mapper;

/**
 * Mapper mapstruct de l'Entity au DTO et l'inverse
 */
@Mapper(componentModel = "spring")
public interface PreReservationMapper {
    PreReservationDto preReservationToPreReservationDto(PreReservation preReservation);
    PreReservation preReservationDtoToPreReservation(PreReservationDto preReservationDto);

}
