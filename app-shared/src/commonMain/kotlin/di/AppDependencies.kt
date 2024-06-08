package org.pointyware.painteddogs.app.di

import org.koin.core.component.KoinComponent
import org.pointyware.painteddogs.feature.collections.core.di.FundDependencies
import org.pointyware.painteddogs.feature.payments.di.PaymentDependencies
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies

/**
 * Top level dependencies for the app
 */
interface AppDependencies {
    fun provideProfileDependencies(): ProfileDependencies
    fun provideFundDependencies(): FundDependencies
    fun providePaymentDependencies(): PaymentDependencies
}

fun getDependencies(): AppDependencies = KoinAppDependencies()

class KoinAppDependencies: AppDependencies, KoinComponent {
    override fun provideProfileDependencies(): ProfileDependencies {
        TODO("Not yet implemented")
    }

    override fun provideFundDependencies(): FundDependencies {
        TODO("Not yet implemented")
    }

    override fun providePaymentDependencies(): PaymentDependencies {
        TODO("Not yet implemented")
    }
}

class KoinMockAppDependencies: AppDependencies, KoinComponent {
    override fun provideProfileDependencies(): ProfileDependencies {
        TODO("Not yet implemented")
    }

    override fun provideFundDependencies(): FundDependencies {
        TODO("Not yet implemented")
    }

    override fun providePaymentDependencies(): PaymentDependencies {
        TODO("Not yet implemented")
    }
}
