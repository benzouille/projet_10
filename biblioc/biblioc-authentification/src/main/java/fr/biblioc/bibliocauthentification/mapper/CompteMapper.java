package fr.biblioc.bibliocauthentification.mapper;

import fr.biblioc.bibliocauthentification.dto.CompteDto;
import fr.biblioc.bibliocauthentification.model.Compte;
import org.mapstruct.Mapper;

/**
 * Interface de mapping entre entity et Dto
 */
@Mapper(componentModel = "spring")
public interface CompteMapper {
    CompteDto compteToCompteDto(Compte compte);
    Compte compteDtoToCompte(CompteDto compteDto);
}