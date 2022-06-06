package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;

public class UsedAmmoPolicy implements EndingPolicy {

	private final Supplier<Integer> ammo;
	private final Predicate<User> condition;

	private UsedAmmoPolicy(Supplier<Integer> ammo, @Nullable Predicate<User> condition) {
		this.ammo = ammo;
		this.condition = condition;
	}

	public static UsedAmmoPolicy of(Supplier<Integer> ammo) {
		return new UsedAmmoPolicy(ammo, null);
	}

	public static UsedAmmoPolicy of(Supplier<Integer> ammo, @Nullable Predicate<User> condition) {
		return new UsedAmmoPolicy(ammo, condition);
	}

	@Override
	public boolean test(User user) {
		return ammo.get() <= 0 && (condition == null || condition.test(user));
	}
}
