/*
 *    Copyright 2010 The myBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.maven.mvnmigrate;

import org.apache.ibatis.migration.commands.DownCommand;
import org.apache.ibatis.migration.commands.PendingCommand;
import org.apache.ibatis.migration.commands.UpCommand;

/**
 * @version $Id$
 */
@SuppressWarnings("unchecked")
public class PendingCommandMojoTest extends AbstractMigrateTestCase {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initEnvironment();
    }

    public void testUpGoal() throws Exception {
        AbstractCommandMojo<UpCommand> mojo = (AbstractCommandMojo<UpCommand>) lookupMojo("up", testPom);
        assertNotNull(mojo);
        setVariableValueToObject(mojo, "upSteps", null);
        mojo.execute();
    }

    public void testPengingGoal() throws Exception {
        AbstractCommandMojo<PendingCommand> mojo = (AbstractCommandMojo<PendingCommand>) lookupMojo("pending", testPom);
        assertNotNull(mojo);
        mojo.execute();
    }

    public void testDownGoal() throws Exception {
        AbstractCommandMojo<DownCommand> mojo = (AbstractCommandMojo<DownCommand>) lookupMojo("down", testPom);
        assertNotNull(mojo);
        setVariableValueToObject(mojo, "downSteps", "1");
        mojo.execute();
    }

    public void testALLDownGoal() throws Exception {
        AbstractCommandMojo<DownCommand> mojo = (AbstractCommandMojo<DownCommand>) lookupMojo("down", testPom);
        assertNotNull(mojo);
        setVariableValueToObject(mojo, "downSteps", "ALL");
        mojo.execute();
    }

}