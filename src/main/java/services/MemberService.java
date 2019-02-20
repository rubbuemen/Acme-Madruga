
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MemberRepository;
import domain.Member;

@Service
@Transactional
public class MemberService {

	// Managed repository
	@Autowired
	private MemberRepository	memberRepository;


	// Supporting services

	// Simple CRUD methods
	public Member create() {
		Member result;

		result = new Member();

		return result;
	}

	public Collection<Member> findAll() {
		Collection<Member> result;

		result = this.memberRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Member findOne(final int memberId) {
		Assert.isTrue(memberId != 0);

		Member result;

		result = this.memberRepository.findOne(memberId);
		Assert.notNull(result);

		return result;
	}

	public Member save(final Member member) {
		Assert.notNull(member);

		Member result;

		result = this.memberRepository.save(member);

		return result;
	}

	public void delete(final Member member) {
		Assert.notNull(member);
		Assert.isTrue(member.getId() != 0);
		Assert.isTrue(this.memberRepository.exists(member.getId()));

		this.memberRepository.delete(member);
	}

	// Other business methods

	// Reconstruct methods

}
