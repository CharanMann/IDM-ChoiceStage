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

import org.forgerock.json.JsonValue;
import org.forgerock.json.resource.BadRequestException;
import org.forgerock.json.resource.ResourceException;
import org.forgerock.selfservice.core.ProcessContext;
import org.forgerock.selfservice.core.ProgressStage;
import org.forgerock.selfservice.core.StageResponse;
import org.forgerock.selfservice.core.util.RequirementsBuilder;

import java.util.List;

import static org.forgerock.json.JsonValue.json;
import static org.forgerock.selfservice.core.util.RequirementsBuilder.oneOf;

/**
 * Choice stage to select require choice.
 *
 * @since 0.7.0
 */
public final class ChoiceStage implements ProgressStage<ChoiceStageConfig> {

    @Override
    public JsonValue gatherInitialRequirements(
            ProcessContext context, ChoiceStageConfig config) throws ResourceException {

        JsonValue[] choicesJson = generateChoiceJSONArray(config.getChoices());

        return RequirementsBuilder
                .newInstance("Choices")
                .addRequireProperty("Choices", "Choices")
                .addDefinition("Choices", oneOf(choicesJson))
                .build();
    }

    private JsonValue[] generateChoiceJSONArray(List<String> choices) {
        JsonValue[] choicesJson = new JsonValue[choices.size()];
        int i = 0;

        for (String choice : choices) {
            choicesJson[i] = json(choice);
            i++;
        }

        return choicesJson;
    }

    @Override
    public StageResponse advance(
            ProcessContext context, ChoiceStageConfig config) throws ResourceException {

        JsonValue selectedChoice = context.getInput().get("selectedChoice");

        if (selectedChoice.isNull()) {
            throw new BadRequestException("No option selected");
        }

        context.putState(ChoiceStageConfig.SELECTED_CHOICE_FIELD, selectedChoice.asString());

        return StageResponse.newBuilder().build();
    }


}
