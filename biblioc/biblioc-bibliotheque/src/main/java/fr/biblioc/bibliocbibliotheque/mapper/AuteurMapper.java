package fr.biblioc.bibliocbibliotheque.mapper;

import fr.biblioc.bibliocbibliotheque.dto.AuteurDto;
import fr.biblioc.bibliocbibliotheque.model.Auteur;
import org.mapstruct.Mapper;

/**
 * Interface de mapping entre entity et Dto
 */
@Mapper(componentModel = "spring")
public interface AuteurMapper {
    AuteurDto auteurToAuteurDto(Auteur auteur);
    Auteur auteurDtoToAuteur(AuteurDto auteurDto);
}
