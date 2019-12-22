package validation.seq;

import javax.validation.GroupSequence;

/**
 * バリデート順を規定するためのインタフェース.
 */
@GroupSequence({FirstCheck.class, SecondCheck.class})
public interface CheckSequence {
}
