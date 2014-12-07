package com.hui.webservicetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	GradeInfoTest.class,
	HelpInfoTest.class,
	MessageTest.class,
	PraiseTest.class,
	QuestionTest.class,
	SubmitBugTest.class,
	SysDataTest.class,
	TeacherInfoTest.class,
	UserAnswerTest.class,
	UserFollowTest.class,
	UserPhotoTest.class,
	UserInfoTest.class,
	UserRechargeTest.class
})
public class AllTest {

}
