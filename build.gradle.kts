plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.composeHelper).apply(false)
    alias(libs.plugins.composeMultiplatform).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinJvm).apply(false)

    // apply dokka now
    alias(libs.plugins.dokka)

//    alias(libs.plugins.painteddogs.koin).apply(false)
//    alias(libs.plugins.painteddogs.kmp).apply(false)
//    alias(libs.plugins.painteddogs.coreProjects).apply(false)
//    alias(libs.plugins.painteddogs.compose).apply(false)
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

dependencies {
    dokka(projects.core)
    dokka(projects.core.entities)
    dokka(projects.core.data)
    dokka(projects.core.remote)
    dokka(projects.core.local)
    dokka(projects.core.interactors)
    dokka(projects.core.viewModels)
    dokka(projects.core.ui)
    dokka(projects.core.ads)
    dokka(projects.core.analytics)

    dokka(projects.feature)
    dokka(projects.feature.collections)
    dokka(projects.feature.collections.core)
    dokka(projects.feature.collections.crowdfunding)
    dokka(projects.feature.chat)
    dokka(projects.feature.events)
    dokka(projects.feature.payments)
    dokka(projects.feature.payments.core)
    dokka(projects.feature.login)
    dokka(projects.feature.profiles)
    dokka(projects.feature.groups)
}
