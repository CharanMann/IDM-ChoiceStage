/*
 * Copyright © 2019 ForgeRock, AS.
 *
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Portions Copyrighted 2019 Charan Mann
 *
 * IDM-ChoiceStage: Created by Charan Mann on 2019-02-28 , 13:19.
 */

package org.forgerock.selfservice.custom;

import org.forgerock.selfservice.core.config.StageConfig;

import java.util.List;
import java.util.Objects;

/**
 * Represents the config for a Choice stage
 *
 * @since 0.7.0
 */
public final class ChoiceStageConfig implements StageConfig {

    /**
     * State field for selected choice
     */
    public static final String SELECTED_CHOICE_FIELD = "selectedChoice";

    private static final String NAME = "Choice";

    private List<String> choices;

    /**
     * Gets the choices.
     *
     * @return choices
     */
    public List<String> getChoices() {
        return choices;
    }

    /**
     * Sets the choices
     *
     * @param choices
     * @return this config
     */
    public ChoiceStageConfig setChoices(List<String> choices) {
        this.choices = choices;
        return this;
    }

    @Override
    public String getProgressStageClassName() {
        return ChoiceStage.class.getName();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getProgressStageClassName(), choices);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChoiceStageConfig)) {
            return false;
        }

        ChoiceStageConfig that = (ChoiceStageConfig) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getProgressStageClassName(), that.getProgressStageClassName())
                && Objects.equals(choices, that.choices);
    }
}
