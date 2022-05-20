/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.jarcompatibilitychecker.test;

import net.minecraftforge.jarcompatibilitychecker.core.IncompatibilityMessages;
import org.junit.Test;

import java.nio.file.Path;

public class SuperClassTests extends BaseCompatibilityTest {
    @Override
    protected Path getRoot() {
        return super.getRoot().resolve("SuperClass");
    }

    @Test
    public void testApiMissingPackagePrivateSuperClass() {
        // A missing package-private superclass is API compatible
        assertCompatible(false, "MissingPackagePrivateSuperClass", "A");
    }

    @Test
    public void testMissingPackagePrivateSuperClass() {
        // A missing public superclass is binary incompatible
        assertClassIncompatible(true, "MissingPackagePrivateSuperClass", "A", IncompatibilityMessages.CLASS_MISSING_SUPERCLASS, "C");
    }

    @Test
    public void testApiMissingPublicSuperClass() {
        // A missing public superclass is API incompatible
        assertClassIncompatible(false, "MissingPublicSuperClass", "A", IncompatibilityMessages.CLASS_MISSING_SUPERCLASS, "C");
    }

    @Test
    public void testMissingPublicSuperClass() {
        // A missing public superclass is binary incompatible
        assertClassIncompatible(true, "MissingPublicSuperClass", "A", IncompatibilityMessages.CLASS_MISSING_SUPERCLASS, "C");
    }

    @Test
    public void testApiNewSuperClass() {
        // Inserting a new superclass into a class hierarchy while preserving the old hierarchy is API compatible
        assertCompatible(false, "NewSuperClass", "A");
    }

    @Test
    public void testNewSuperClass() {
        // Inserting a new superclass into a class hierarchy while preserving the old hierarchy is binary compatible
        assertCompatible(true, "NewSuperClass", "A");
    }
}