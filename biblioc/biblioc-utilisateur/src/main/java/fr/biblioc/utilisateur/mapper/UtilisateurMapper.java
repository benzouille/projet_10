package fr.biblioc.utilisateur.mapper;

import fr.biblioc.utilisateur.dto.UtilisateurDto;
import fr.biblioc.utilisateur.model.Utilisateur;
import org.mapstruct.Mapper;

/**
 * Interface de mapping entre entity et Dto
 */
@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDto utilisateurToUtilisateurDto(Utilisateur utilisateur);
    Utilisateur utilisateurDtoToUtilisateur(UtilisateurDto utilisateurDto);
}
