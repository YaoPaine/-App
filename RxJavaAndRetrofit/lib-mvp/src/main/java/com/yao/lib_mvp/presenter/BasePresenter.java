package com.yao.lib_mvp.presenter;

import com.yao.lib_mvp.model.data.DataRepository;
import com.yao.lib_mvp.view.IView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: 抽象的公用Presenter
 * @Author: YaoPaine
 * @CreateDate: 下午4:19
 * @Version:
 */

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mMvpView;//所有view
    protected DataRepository mDataCenter;//数据中心
    protected CompositeDisposable mCompositeDisposable;//订阅管理

    /**
     * @return 获取V
     */
    public T getMvpView() {
        return mMvpView;
    }

    /**
     * view绑定P的时候初始化
     */
    @Override
    public void attachView(T view) {
        mMvpView = view;
        mCompositeDisposable = new CompositeDisposable();
        this.mDataCenter = new DataRepository();
    }

    /**
     * view失去绑定清除
     */
    @Override
    public void detachView() {
        unSubscribe();
        this.mMvpView = null;
        this.mDataCenter = null;
    }

    @Override
    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 统一添加订阅关联被观察者和观察者
     */
    public <N> void addSubscription(Observable<N> observable, DisposableObserver<N> subscriber) {
        if (observable == null || subscriber == null)
            throw new RuntimeException("Observable or Observer should not be null");

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber));

    }

    /**
     * 当前的view（fragment & activity是否存在）
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * 是否view绑定过P
     */
    public void checkViewAttached() {
        if (isViewAttached()) throw new MvpViewNotAttachedException();
    }

    /**
     * p&v没有绑定的异常
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}
