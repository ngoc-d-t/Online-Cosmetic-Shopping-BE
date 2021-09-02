package com.ngocdt.tttn.dto;


import com.ngocdt.tttn.entity.Character;
import com.ngocdt.tttn.service.CharacterService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;

@Getter
@Setter
public class CharacterDTO {
    private int characterID;
    @NotBlank(message = "can be not empty.")
    private String description;

    public static CharacterDTO toDTO(Character character){
        if(character == null)
            return null;
        CharacterDTO dto = new CharacterDTO();
        dto.setCharacterID(character.getCharacterID());
        dto.setDescription(character.getDescription());
        return dto;
    }
    public static Character toEntity(CharacterDTO dto){
        if(dto == null)
            return null;
        Character chara = new Character();
        chara.setCharacterID(dto.getCharacterID());
        chara.setDescription(dto.getDescription());
        return chara;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
