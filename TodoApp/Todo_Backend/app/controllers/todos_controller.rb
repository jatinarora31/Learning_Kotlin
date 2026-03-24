class TodosController < ApplicationController
  skip_before_action :verify_authenticity_token
  before_action :set_todo, only: %i[ show edit update destroy ]

  def index
    todos = Todo.all
    render json: todos
  end

  def show
    render json: @todo
  end

  def new
    @todo = Todo.new
  end

  def create
    @todo = Todo.new(todo_params)
    @todo.is_completed = false

    respond_to do |format|
      if @todo.save
        format.json { render :show, status: :created, location: @todo }
      else
        format.json { render json: @todo.errors, status: :unprocessable_entity }
      end
    end
  end

  def update
    respond_to do |format|
      if @todo.update(todo_params)
        format.json { render :show, status: :ok, location: @todo }
      else
        format.json { render json: @todo.errors, status: :unprocessable_entity }
      end
    end
  end

  def destroy
    @todo.destroy!

    respond_to do |format|
      format.json { head :no_content }
    end
  end

  private
    def set_todo
      @todo = Todo.find(params[:id])
    end

    def todo_params
      params.require(:todo).permit(:title, :description)
    end
end
