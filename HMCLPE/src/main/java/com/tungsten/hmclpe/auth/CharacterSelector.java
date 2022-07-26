package com.tungsten.hmclpe.auth;

import com.tungsten.hmclpe.auth.yggdrasil.GameProfile;
import com.tungsten.hmclpe.auth.yggdrasil.YggdrasilService;

import java.util.List;

/**
 * This interface is for your application to open a GUI for user to choose the character
 * when a having-multi-character yggdrasil account is being logging in.
 */
public interface CharacterSelector {

    /**
     * Select one of {@code names} GameProfiles to login.
     * @param names available game profiles.
     * @throws NoSelectedCharacterException if cannot select any character may because user close the selection window or cancel the selection.
     * @return your choice of game profile.
     */
    GameProfile select(YggdrasilService yggdrasilService, List<GameProfile> names) throws NoSelectedCharacterException;

}