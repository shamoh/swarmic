/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.swarmic.security.impl;

import org.swarmic.security.api.Identity;
import org.swarmic.security.api.LoggedIn;
import org.swarmic.security.api.NotLoggedInException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LoggedIn
@Priority(Interceptor.Priority.APPLICATION + 100)
public class LoggedInInterceptor {
    @Inject
    private Identity identity;

    @AroundInvoke
    public Object verifyLoggedIn(InvocationContext invocationContext) throws Exception {
        if(identity.isLoggedIn()) {
            return invocationContext.proceed();
        }
        else {
            throw new NotLoggedInException(identity+" Not logged in");
        }
    }
}
