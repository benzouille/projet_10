package fr.biblioc.bibliocreservation.mapper;

import fr.biblioc.bibliocreservation.dto.ExemplaireDto;
import fr.biblioc.bibliocreservation.model.Exemplaire;
import org.mapstruct.Mapper;

/**
 * Mapper mapstruct de l'Entity au DTO et l'inverse
 */
@Mapper(componentModel = "spring")
public interface ExemplaireMapper {
    ExemplaireDto exemplaireToExemplaireDto(Exemplaire exemplaire);
    Exemplaire exemplaireDtoToExemplaire(ExemplaireDto exemplaireDto);
}
